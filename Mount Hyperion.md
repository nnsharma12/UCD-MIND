sshfs natasha@hyperion:/home/natasha /users/Natasha/Desktop/Hyperion_Mount -o volname=Hyperion_Mount



sshfs app@monolith:/media/data2/APP_longitudinal /users/Natasha/Desktop/APP_longitudinal_mount -o volname=APP_longitudinal_mount



to unmount: umount ~/Desktop/Hyperion_Mount

===NOTES===
-o defer_permissions -o volname=Server
-o defer_permissions is important! The default behavior is use the normal system of checking UIDs for file access. Unless you have the same UID on both your Mac and the remote system, this is probably not going to work. defer_permissions let’s the remote system handle permission checks, if you can access a file on the server, you will be to access it locally. If you’re the kind of person who make sure that your UIDs are the same everywhere, then you need help, but you don’t need defer_permissions.

-o volname=Server sets the name that the volume will have in the Finder. Otherwise it’s called something like OSXFUSE Volume 0 (sshfs)
https://stuff-things.net/2015/05/20/fuse-and-sshfs-on-os-x/