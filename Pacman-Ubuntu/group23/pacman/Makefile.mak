all:
javac controller/*.java


clean:
rm -rf group23/pacman/model/*.class
rm -rf group23/pacman/controller/*.class
rm -rf group23/pacman/view/*.class
rm -rf *.class
