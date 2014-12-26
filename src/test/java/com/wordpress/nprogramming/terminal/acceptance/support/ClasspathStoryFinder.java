package com.wordpress.nprogramming.terminal.acceptance.support;

import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.WildcardFileFilter;
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
    private static final Logger LOG = LoggerFactory.getLogger(ClasspathStoryFinder.class);

    public static List<String> findFileNamesThatMatch(String aFilenameWithWildcards) {
        return findFilesThatMatch(aFilenameWithWildcards).stream()
                .map(file -> file.toURI().toString())
                .collect(Collectors.toList());
    }

    private static Collection<File> findFilesThatMatch(String aFilenameWithWildcards) {
        WildcardFileFilter regexFileFilter = new WildcardFileFilter(aFilenameWithWildcards);
        List<File> rootDirsToSearchFrom = getRootDirs();
        LOG.info("Searching for stories called [{}] in [{}]", aFilenameWithWildcards, rootDirsToSearchFrom);

        List<File> ret = new ArrayList<>() ;
        for (File f : rootDirsToSearchFrom) {
            ret.addAll(listFiles(f, regexFileFilter, DirectoryFileFilter.DIRECTORY)) ;
        }
        return ret ;
    }

    private static List<File> getRootDirs() {
        List<File> ret = new ArrayList<>() ;
        try {
            Enumeration<URL> roots = ClasspathStoryFinder.class.getClassLoader().getResources("") ;
            while(roots.hasMoreElements()) {
                ret.add(new File(roots.nextElement().getFile())) ;
            }
        } catch(IOException ioe) {
            LOG.error("Failed to derive classpath from Class Loader", ioe) ;
        }
        return ret ;
    }
}
