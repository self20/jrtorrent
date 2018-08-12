package com.wizzardo.jrt;

import com.wizzardo.http.TokenizedFileTreeHandler;
import com.wizzardo.http.filter.TokenFilter;
import com.wizzardo.http.request.Header;
import com.wizzardo.http.request.Request;
import com.wizzardo.http.response.Response;

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by wizzardo on 03/12/16.
 */
public class M3UHandler extends TokenizedFileTreeHandler {

    protected Set<String> extensions = new HashSet<>(Arrays.asList(
            "mkv",
            "avi",
            "mp4",
            "mp3",
            "aac",
            "flac",
            "ape",
            "wav",
            "ts"
    ));

    protected FileFilter fileFilter = pathname -> {
        String name = pathname.getName();
        int i = name.lastIndexOf('.');
        if (i == -1 || i == name.length() - 1)
            return false;
        String extension = name.substring(i + 1);
        return extensions.contains(extension.toLowerCase());
    };

    public M3UHandler(String workDir, String prefix, TokenFilter tokenFilter, String name) {
        super(workDir, prefix, tokenFilter, name);
    }

    @Override
    protected Response handleDirectory(Request request, Response response, String path, File file) {
        StringBuilder sb = new StringBuilder();
        HandlerContextWithToken handlerContext = createHandlerContext(path, request);
        return response.setBody(addFileRecursively(file, sb, handlerContext).toString())
                .header(Header.KEY_CONTENT_TYPE, "audio/x-mpegurl");
    }

    protected StringBuilder addFileRecursively(File file, StringBuilder sb, HandlerContextWithToken context) {
        if (file.isDirectory()) {
            File[] files = file.listFiles(fileFilter);
            if (files != null)
                for (File f : files) {
                    addFileRecursively(f, sb, context);
                }
        } else {
            sb.append(generateUrl(file, context)).append("\n");
        }
        return sb;
    }

}