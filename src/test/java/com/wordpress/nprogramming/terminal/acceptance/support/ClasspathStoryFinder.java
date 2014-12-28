package com.wordpress.nprogramming.terminal.acceptance.support;

import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.NameFileFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;

import static org.apache.commons.io.FileUtils.listFiles;

public final class ClasspathStoryFinder {

    private static final Logger LOG =
            LoggerFactory.getLogger(ClasspathStoryFinder.class);

    public static List<String> findFileNamesThatMatch(
            String aFilename) {

        return findFilesThatMatch(aFilename).stream()
                .map(file -> file.toURI().toString())
                .collect(Collectors.toList());
    }

    private static Collection<File> findFilesThatMatch(
            String aFilename) {

        IOFileFilter fileFilter =
                new NameFileFilter(aFilename);

        List<File> rootDirsToSearchFrom = rootDirs();
        LOG.info("Searching for stories called [{}] in [{}]",
                aFilename, rootDirsToSearchFrom);

        return matchedFiles(fileFilter, rootDirsToSearchFrom);
    }

    private static Collection<File> matchedFiles(
            IOFileFilter fileFilter, List<File> rootDirsToSearchFrom) {

        List<File> matchedFiles = new ArrayList<>();

        for (File f : rootDirsToSearchFrom) {
            matchedFiles.addAll(
                    listFiles(f, fileFilter, DirectoryFileFilter.DIRECTORY));
        }

        return matchedFiles;
    }

    private static List<File> rootDirs() {
        List<File> rootDirs = new ArrayList<>() ;

        try {
            Enumeration<URL> roots =
                    ClasspathStoryFinder.class
                            .getClassLoader().getResources("");

            while(roots.hasMoreElements()) {
                rootDirs.add(new File(roots.nextElement().getFile())) ;
            }
        } catch(IOException ioe) {
            LOG.error("Failed to derive classpath from Class Loader", ioe) ;
        }

        return rootDirs;
    }
}
