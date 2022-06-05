package com.cone.app;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.cone.services.utils.FileLocator;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    @Test
    public void whenRequestingListOfEntries_thenSuccess()
    {
        String[] args = new String[1];
        args[0] = "-ls";
        App.main(args);
    }

    @Test
    public void whenInitialized_whenLookingForDataFile_thenSuccess() throws URISyntaxException, IOException
    {
        String[] args = new String[1];
        args[0] = "-ls";
        App.main(args);

        String dataFilePath = FileLocator.getPath(App.ENTRY_FILE_PATH);
        File dataFile = new File(dataFilePath);
        Assertions.assertEquals(true, dataFile.exists());

        File dataDir = dataFile.getParentFile();
        FileUtils.deleteDirectory(dataDir);
    }
}
