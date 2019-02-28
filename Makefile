# Ensimag 2A POO - TP 2015/16
# ============================
#
# Ce Makefile permet de compiler le test de l'ihm en ligne de commande.
# Alternative (recommandee?): utiliser un IDE (eclipse, netbeans, ...)
# Le but est d'illustrer les notions de "classpath", a vous de l'adapter
# a votre projet.
#
# Organisation:
#  1) Les sources (*.java) se trouvent dans le repertoire src
#     Les classes d'un package toto sont dans src/toto
#     Les classes du package par defaut sont dans src
#
#  2) Les bytecodes (*.class) se trouvent dans le repertoire bin
#     La hierarchie des sources (par package) est conservee.
#     Pour un package (ici gui.jar), il est aussi dans bin.
#
# Compilation:
#  Options de javac:
#   -d : repertoire dans lequel sont places les .class compiles
#   -classpath : repertoire dans lequel sont cherches les .class deja compiles
#   -sourcepath : repertoire dans lequel sont cherches les .java (dependances)

all: Balles Jeu_Vie Jeu_Imi Jeu_Seg Boidss Evenements

Balles: Ball Balls BallsSimulator TestBallsSimulator 
Jeu_Vie: Cellule Grille JeuVieSimulator TestJeuVieSimulator
Jeu_Imi: Cellule Grille JeuImiSimulator TestJeuImiSimulator
Jeu_Seg: Cellule Grille GrilleSeg JeuSegSimulator TestJeuSegSimulator
Boidss: Vector Boid Boids BoidsSimulator TestBoidsSimulator 
Evenements: Event EventManager BoidsEvent 

testGUI:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/TestGUI.java

TestBallsSimulator:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/TestBallsSimulator.java

Ball:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/Ball.java

Balls:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/Balls.java

BallsSimulator:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/BallsSimulator.java

Cellule:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/Cellule.java

Grille:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/Grille.java

GrilleSeg:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/GrilleSeg.java

JeuVieSimulator:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/JeuVieSimulator.java

TestJeuVieSimulator:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/TestJeuVieSimulator.java

JeuImiSimulator:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/JeuImiSimulator.java

TestJeuImiSimulator:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/TestJeuImiSimulator.java

JeuSegSimulator:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/JeuSegSimulator.java

TestJeuSegSimulator:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/TestJeuSegSimulator.java

Boid:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/Boid.java

Boids:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/Boids.java

BoidsSimulator:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/BoidsSimulator.java

TestBoidsSimulator:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/TestBoidsSimulator.java

Vector:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/Vector.java

Event:
	javac -d bin -sourcepath src src/Event.java

EventManager:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/EventManager.java

TestEventManager:
	javac -d bin -sourcepath src src/TestEventManager.java

MessageEvent:
	javac -d bin -sourcepath src src/MessageEvent.java

BallsEvent:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/BallsEvent.java

BoidsEvent:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/BoidsEvent.java


Execution:
# on peut taper directement la ligne de commande :
#   > java -classpath bin TestGUI
# ou bien lancer l'execution en passant par ce Makefile:
#   > make exeIHM
exeGUI:
	java -classpath bin:bin/gui.jar TestGUI

exeBalls:
	java -classpath bin:bin/gui.jar TestBallsSimulator

exeVie:
	java -classpath bin:bin/gui.jar TestJeuVieSimulator

exeImi:
	java -classpath bin:bin/gui.jar TestJeuImiSimulator

exeSeg:
	java -classpath bin:bin/gui.jar TestJeuSegSimulator

exeBoids:
	java -classpath bin:bin/gui.jar TestBoidsSimulator

exeMessage:
	java -classpath bin TestEventManager

clean:
	rm -rf bin/*.class
	rm -f src/*.class
