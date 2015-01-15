/*
 * Copyright 2015 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package io.netty.channel.epoll;

import java.io.IOException;

public final class FileDescriptor {

    /**
     * An invalid file descriptor which was closed before.
     */
    public static final FileDescriptor INVALID = new FileDescriptor();

    private final int fd;

    public FileDescriptor(int fd) {
        if (fd < 0) {
            throw new IllegalArgumentException("fd must be >= 0");
        }
        this.fd = fd;
    }

    private FileDescriptor() {
        fd = -1;
    }

    /**
     * Return the int value of the filedescriptor.
     */
    public int intValue() {
        return fd;
    }

    /**
     * Close the file descriptor.
     */
    public void close() throws IOException {
        Native.close(fd);
    }

    @Override
    public String toString() {
        return "FileDescriptor{" +
                "fd=" + fd +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FileDescriptor)) {
            return false;
        }

        return fd == ((FileDescriptor) o).fd;
    }

    @Override
    public int hashCode() {
        return fd;
    }
}
