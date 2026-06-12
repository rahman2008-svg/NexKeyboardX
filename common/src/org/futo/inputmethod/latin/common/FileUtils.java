package com.nexvora.keyboard.latin.common;

import java.io.File;
import java.io.FilenameFilter;

/**
 * Utility class for file operations used in keyboard engine,
 * including recursive deletion and safe file replacement.
 *
 * This is mainly used for dictionary management, cache cleanup,
 * and temporary data handling.
 */
public class FileUtils {

    private static final String TAG = "NexVoraFileUtils";

    /**
     * Recursively deletes a file or directory.
     *
     * @param path file or directory to delete
     * @return true if deletion succeeded
     */
    public static boolean deleteRecursively(final File path) {

        if (path.isDirectory()) {
            final File[] files = path.listFiles();

            if (files != null) {
                for (final File child : files) {
                    deleteRecursively(child);
                }
            }
        }

        return path.delete();
    }

    /**
     * Deletes files matching a filter inside a directory.
     *
     * @param dir target directory
     * @param fileNameFilter filter condition
     * @return true if all matched files were deleted successfully
     */
    public static boolean deleteFilteredFiles(final File dir,
                                              final FilenameFilter fileNameFilter) {

        if (!dir.isDirectory()) {
            return false;
        }

        final File[] files = dir.listFiles(fileNameFilter);

        if (files == null) {
            return false;
        }

        boolean hasDeletedAllFiles = true;

        for (final File file : files) {
            if (!deleteRecursively(file)) {
                hasDeletedAllFiles = false;
            }
        }

        return hasDeletedAllFiles;
    }

    /**
     * Safely renames a file by deleting the target first.
     *
     * @param fromFile source file
     * @param toFile destination file
     * @return true if rename succeeded
     */
    public static boolean renameTo(final File fromFile,
                                   final File toFile) {

        toFile.delete();
        return fromFile.renameTo(toFile);
    }
}
