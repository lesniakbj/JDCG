package dcsgen.util;

import dcsgen.DCSMissionGenerator;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * (c) Copyright 2018 Calabrio, Inc.
 * All Rights Reserved. www.calabrio.com LICENSED MATERIALS
 * Property of Calabrio, Inc., Minnesota, USA
 * <p>
 * No part of this publication may be reproduced, stored or transmitted,
 * in any form or by any means (electronic, mechanical, photocopying,
 * recording or otherwise) without prior written permission from Calabrio Software.
 * <p>
 *
 * Created by Brendan.Lesniak on 7/27/2018.
 */
public class ZipUtil {
    private static final Logger log = LogManager.getLogger(ZipUtil.class);

    public static void zipFiles(String zipName, List<File> files) {
        File mizFile = new File(zipName);

        try(ZipOutputStream out = new ZipOutputStream(new FileOutputStream(mizFile))) {
            for (File file : files) {
                // Create an entry for this file
                ZipEntry ze = new ZipEntry(file.getName());
                out.putNextEntry(ze);

                // Read the file data to a byte array
                byte[] data = FileUtils.readFileToByteArray(file);

                // Write data to out
                out.write(data, 0, data.length);
                out.closeEntry();
            }
        } catch (FileNotFoundException e) {
            log.debug("Couldn't find file when writing zip!");
            log.debug(e);
        } catch (IOException e) {
            log.debug("IO Exception when trying to write zip!");
            log.debug(e);
        }
    }
}
