/*
 *  MediathekView
 *  Copyright (C) 2008 W. Xaver
 *  W.Xaver[at]googlemail.com
 *  http://zdfmediathk.sourceforge.net/
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package mediathek;

import java.awt.SplashScreen;
import java.io.File;
import java.nio.file.Path;
import java.util.Iterator;
import mediathek.controller.IoXmlLesen;
import mediathek.controller.Log;
import mediathek.daten.Daten;
import mediathek.daten.DatenProg;
import mediathek.daten.DatenPset;

public class MediathekPath {

    private Daten daten;
    private String pfad = "";
    private String pathFrom = "";
    private String pathTo = "";

    public MediathekPath(String[] ar) {
        if (ar != null) {
            if (ar.length > 0) {

                if (!ar[0].startsWith("-")) {
                    if (!ar[0].endsWith(File.separator)) {
                        ar[0] += File.separator;
                    }
                    pfad = ar[0];
                }

                for (int i = 0; i < ar.length; ++i) {
                    if (ar[i].equalsIgnoreCase("-pathFrom")) {
                        if (ar.length >= i + 2) {
                            pathFrom = ar[i + 1];
                        }
                    }
                    if (ar[i].equalsIgnoreCase("-pathTo")) {
                        if (ar.length >= i + 2) {
                            pathTo = ar[i + 1];
                        }
                    }
                }

            }
        }
        final SplashScreen splash = SplashScreen.getSplashScreen();
        if (splash != null) {
            splash.close();
        }
    }

    public void starten() {
        daten = new Daten(pfad, null);
        Daten.auto = true;
        Log.startMeldungen();

        if (!IoXmlLesen.einstellungenExistieren()) {
            // Programm erst mit der GuiVersion einrichten
            Log.fehlerMeldung(720369874, "Das Programm muss erst mit der Gui-Version eingerichtet werden!");
            System.exit(1);
        }

        // Einstellungen laden
        Path xmlFilePath = Daten.getMediathekXmlFilePath();
        Log.systemMeldung("Einstellungen laden: " + xmlFilePath.toString());
        if (!IoXmlLesen.datenLesen(xmlFilePath)) {
            // dann hat das Laden nicht geklappt
            Log.fehlerMeldung(410236589, "Einstellungen konnten nicht geladen werden: " + xmlFilePath.toString());
            System.exit(1);
        }
        Log.systemMeldung("Pfade alt: " + pathFrom);
        Log.systemMeldung("Pfade neu: " + pathTo);
        if (pathFrom.isEmpty() || pathTo.isEmpty()) {
            Log.systemMeldung("Pfade alt oder neu sind leer, kann nichts getauscht werden");
        } else {
            if (changePath()) {
                daten.allesSpeichern();
                Log.systemMeldung("Änderungen gespeichert!");
            } else {
                Log.systemMeldung("Fehler beim Tauschen, Änderungen nicht gespeichert!");
            }
        }
        Log.printEndeMeldung();
        System.exit(0);
    }

    private synchronized boolean changePath() {
        boolean ret = false;
        try {
            Iterator<DatenPset> itPset = Daten.listePset.iterator();
            DatenPset datenPset;
            DatenProg datenProg;
            while (itPset.hasNext()) {
                datenPset = itPset.next();
                if (!datenPset.isFreeLine() && !datenPset.isLable()) {
                    // nur wenn kein Lable oder freeline
                    Log.systemMeldung("=============================================");
                    Log.systemMeldung("Programmgruppe: " + datenPset.arr[DatenPset.PROGRAMMSET_NAME_NR]);
                    Iterator<DatenProg> itProg = datenPset.getListeProg().iterator();
                    while (itProg.hasNext()) {
                        datenProg = itProg.next();
                        // Programmpfad prüfen
                        if (datenProg.arr[DatenProg.PROGRAMM_PROGRAMMPFAD_NR].contains(pathFrom)) {
                            Log.systemMeldung("Programmpfad von: " + datenProg.arr[DatenProg.PROGRAMM_PROGRAMMPFAD_NR]);
                            datenProg.arr[DatenProg.PROGRAMM_PROGRAMMPFAD_NR] = datenProg.arr[DatenProg.PROGRAMM_PROGRAMMPFAD_NR].replace(pathFrom, pathTo);
                            Log.systemMeldung("Programmpfad nach: " + datenProg.arr[DatenProg.PROGRAMM_PROGRAMMPFAD_NR]);
                        }
                    }
                    Log.systemMeldung("");
                }
            }
            ret = true;
        } catch (Exception ex) {
            Log.fehlerMeldung(901201458, ex);
        }
        return ret;
    }
}
