package beans;

import java.io.File;

import javax.swing.filechooser.FileFilter;
 
/*
 * This class implements a generic file name filter that allows the listing/selection
 * of JPEG files.
 */
public class JPEGImageFileFilter extends FileFilter implements java.io.FileFilter
  {
  @Override
public boolean accept(File f)
    {
    if (f.getName().toLowerCase().endsWith(".jpeg")) return true;
    if (f.getName().toLowerCase().endsWith(".jpg")) return true;
    return false;
    }
  @Override
public String getDescription()
    {
    return "JPEG files";
    }
  }
 
