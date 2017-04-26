/*
 * Copyright (C) 2001 eTranslate, Inc. All Rights Reserved
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 *
 * Contact: <eric@etranslate.com>
 */

package com.etranslate.tm.processing.rtf;

import java.util.List;
import java.io.OutputStream;

/**
 * Implemented by classes that receive RTFParser messages.
 *
 * Created: Tue Jul  3 10:29:05 2001
 *
 * @author Eric Friedman
 * @version $Id: RTFParserDelegate.java,v 1.2 2001/07/10 03:07:53 eric Exp $
 */

public interface RTFParserDelegate {
    /** CVS version info for this interface */
    public static final String VERSION = "$Id: RTFParserDelegate.java,v 1.2 2001/07/10 03:07:53 eric Exp $";

    /** constants representing RTF contexts in which text events may occur */
    public static final int IN_DOCUMENT = 0;
    public static final int IN_FONTTBL = 1;
    public static final int IN_FILETBL = 2;
    public static final int IN_COLORTBL = 3;
    public static final int IN_STYLESHEET = 4;
    public static final int IN_LISTTABLE = 5;
    public static final int IN_STYLE = 6;
    public static final int IN_REVTBL = 7;
    public static final int IN_INFO = 8;
    public static final int IN_PNTEXT = 9;
    public static final String NO_STYLE = new String();
    
    /**
     * Receive a block of text from the RTF document.  The text is
     * in the named style and occurs in <code>context</code>.
     *
     * <p>Style is guaranteed to have object identity with one of the
     * styles in the list provided by the styleList message, if that
     * has been called.</p>
     *
     * @param text a <code>String</code> value
     * @param style a <code>String</code> value
     * @param context an <code>int</code> value
     * @throws ParseException is thrown when parse errors are encountered.
     */
    public void text(String text, String style, int context) throws ParseException;

    /**
     * Get the next output stream which will be written with binary data
     * encountered in the RTF stream, usually in a \bin command.  The stream
     * will be closed once all of the data has been written to it.
     *
     * @param context an <code>int</code> value
     * @throws ParseException is thrown when parse errors are encountered.
     */
    public OutputStream getNextOutputStream(int context) throws ParseException;

    /**
     * Receive a control symbol in a particular context.
     *
     * @param controlSymbol a <code>String</code> value
     * @param context an <code>int</code> value
     * @throws ParseException is thrown when parse errors are encountered.
     */
    public void controlSymbol(String controlSymbol, int context) throws ParseException;

    /**
     * Receive a control word in a particular context.  The value, if
     * not provided, will be <code>0</code> as per the RTF spec.
     *
     * @param controlWord a <code>String</code> value
     * @param value an <code>long</code> value
     * @param context an <code>int</code> value
     * @throws ParseException is thrown when parse errors are encountered.
     */
    public void controlWord(String controlWord, long value, int context) throws ParseException;

    /**
     * Receive notification about the opening of an RTF group with the
     * specified depth. The depth value is that of the group just opened.
     *
     * @param depth an <code>int</code> value
     * @param context an <code>int</code> value
     * @throws ParseException is thrown when parse errors are encountered.
     */
    public void openGroup(int depth) throws ParseException;

    /**
     * Receive notification about the closing of an RTF group with the
     * specified depth.  The depth value is that of the group just closed.
     *
     * @param depth an <code>int</code> value
     * @throws ParseException is thrown when parse errors are encountered.
     */
    public void closeGroup(int depth) throws ParseException;

    /**
     * Receive notification about the list of style names defined for the
     * document
     *
     * @param styles a <code>List</code> of <code>String</code> objects.
     * @throws ParseException is thrown when parse errors are encountered.
     */
    public void styleList(List<String> styles) throws ParseException;
    
    /**
     * The document parsing has begun.
     *
     * @throws ParseException is thrown when parse errors are encountered.
     */
    public void startDocument() throws ParseException;

    /**
     * Parsing is complete.
     *
     * @throws ParseException is thrown when parse errors are encountered.
     */
    public void endDocument() throws ParseException;

}// RTFParserDelegate
