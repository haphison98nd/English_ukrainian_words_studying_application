package speech;


import org.junit.Test;
import project.entities.item_implementations.words.WordTranslate;
import project.io_data_module.CsvWordsReader;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class SpeechTest
{

	private String PATH_TO_FOLDER = "c:\\dictionaries";
	private String PATH_TO_DICTIONARY = "c:\\dictionaries\\dic.csv";
	private CsvWordsReader reader = new CsvWordsReader();
	private Speech speech = new Speech();

	/** creating mp3 list of words */
	@Test
    private void testSpeechingFromFile()
	{
		List<WordTranslate> list = reader.getItemList(PATH_TO_DICTIONARY);
		for (WordTranslate wordTranslate : list)
		{
			speech.speak(wordTranslate.getEngword());
			speech.speak(wordTranslate.getUkrword());
			new Speech().writeToFile(PATH_TO_FOLDER, wordTranslate);
			try
			{
				TimeUnit.SECONDS.sleep(2);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}

	/** deleting files with null size*/
	@Test
	public void deleteEmptyFiles()
	{
		File folder = new File(PATH_TO_FOLDER);

		for (File f : folder.listFiles((f) -> {
			if (f.isFile())
				return (f.getAbsoluteFile().length() == 0);
			return false;
		}))
		{
			String filename = f.getName();
			if (f.delete())
				System.out.println(filename + " was deleted");
		}
	}
}