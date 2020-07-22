/*
 * Copyright (c) 2020 Dzikoysk
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.panda_lang.utilities.annotations.resource;

import org.panda_lang.utilities.annotations.AnnotationsScannerFile;

import java.io.InputStream;
import java.util.zip.ZipEntry;

/**
 *
 */
final class JarAnnotationsScannerFile implements AnnotationsScannerFile {

    private final ZipEntry entry;
    private final JarAnnotationsScannerResource resource;
    private final long fromIndex;
    private final long endIndex;
    private final String internal;

    JarAnnotationsScannerFile(JarAnnotationsScannerResource resource, ZipEntry entry, long cursor, long nextCursor) {
        this.entry = entry;
        this.resource = resource;
        this.fromIndex = cursor;
        this.endIndex = nextCursor;
        this.internal = getOriginalPath().substring(getOriginalPath().lastIndexOf("/") + 1);
    }

    @Override
    public InputStream openInputStream() {
        return new JarAnnotationsScannerInputStream(resource, fromIndex, endIndex);
    }

    @Override
    public String getInternalPath() {
        return internal;
    }

    @Override
    public String getOriginalPath() {
        return entry.getName();
    }

}
