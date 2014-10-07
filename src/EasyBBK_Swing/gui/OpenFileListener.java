package EasyBBK_Swing.gui;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Locale;

import javax.swing.JFileChooser;
import javax.swing.event.MouseInputListener;
import javax.swing.filechooser.FileFilter;

import EasyBBK_Swing.gui.SaveListener.MyFileFilter;
import data_center.SketchProject;

public class OpenFileListener implements MouseInputListener
{
	public void mouseClicked(MouseEvent e) 
	{
		JFileChooser chooser=new JFileChooser();//文件保存对话框
		chooser.setLocale(Locale.ENGLISH);
		chooser.setAcceptAllFileFilterUsed(true);
		
		if(chooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION)
		{
			File oFile=chooser.getSelectedFile();
			FileFilter filter = chooser.getFileFilter();
			String desc = filter.getDescription();
			String extension = ".xml";
			if (!oFile.getAbsolutePath().toUpperCase().endsWith(extension.toUpperCase()))
			{
				oFile = new File(oFile.getAbsolutePath() + extension);
			}
		}
	}
	
	public void mousePressed(MouseEvent e) {}

	public void mouseDragged(MouseEvent e) {}
	
	public void mouseMoved(MouseEvent e) {}
		
	public void mouseReleased(MouseEvent e){}
		
	public void mouseEntered(MouseEvent e) {}

	public void mouseExited(MouseEvent e) {}
}
