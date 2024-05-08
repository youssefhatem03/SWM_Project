/*
 * jMemorize - Learning made easy (and fun) - A Leitner flashcards tool
 * Copyright(C) 2004-2008 Riad Djemili and contributors
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 1, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 */
package jmemorize.gui.swing.actions.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.filechooser.FileFilter;
import javax.xml.parsers.ParserConfigurationException;

import jmemorize.core.Lesson;
import jmemorize.core.io.XmlBuilder;
import jmemorize.gui.LC;
import jmemorize.gui.Localization;
import jmemorize.gui.swing.frames.MainFrame;
import org.xml.sax.SAXException;

public class ImportJMLAction extends AbstractImportAction {


    private void setValues() {
        setName(Localization.get(LC.FILE_FILTER_DESC));
        setMnemonic(1);
        setIcon("/resource/icons/file_saveas.gif"); //$NON-NLS-1$
    }

    public ImportJMLAction() {
        setValues();
    }

    protected void doImport(File file, Lesson lesson) throws IOException,  ParserConfigurationException, SAXException {
        try {
            if (!file.exists()) {
                throw new FileNotFoundException("File not found: " + file.getAbsolutePath());
            }
            if (!file.canRead()) {
                throw new IOException("Cannot read file: " + file.getAbsolutePath());
            }

            XmlBuilder.loadFromXMLFile(file, lesson);
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File not found: " + file.getAbsolutePath());
        } catch (IOException e) {
            throw new IOException("Error reading file: " + file.getAbsolutePath(), e);
        } catch (Exception e) {
            throw new IOException("Unexpected error: " + e.getMessage(), e);
        }
    }

    protected FileFilter getFileFilter() {
        return MainFrame.FILE_FILTER;
    }

}
