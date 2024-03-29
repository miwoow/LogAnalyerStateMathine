#
# The contents of this file are subject to the Mozilla Public
# License Version 1.1 (the "License"); you may not use this file
# except in compliance with the License. You may obtain a copy
# of the License at http://www.mozilla.org/MPL/
# 
# Software distributed under the License is distributed on an
# "AS IS" basis, WITHOUT WARRANTY OF ANY KIND, either express or
# implied. See the License for the specific language governing
# rights and limitations under the License.
#
# The Original Code is State Machine Compiler (SMC).
#
# The Initial Developer of the Original Code is Charles W. Rapp.
# Portions created by Charles W. Rapp are
# Copyright (C) 2000 - 2006. Charles W. Rapp.
# All Rights Reserved.
#
# Contributor(s):
#
# RCS ID
# $Id: Makefile,v 1.11 2009/03/01 18:20:38 cwrapp Exp $
#
# CHANGE LOG
# (See the bottom of this file.)
#

#################################################################
# Macros.
#

SM_SOURCES=     LogAnaly.sm
SOURCES=        $(SM_SOURCES:%.sm=%Context.java) \
				IClassifier.java \
				IpClassifier.java \
				DefaultClassifier.java \
				FilterChain.java \
				IFilter.java \
				IpFilter.java \
				StatusFilter.java \
                ARule.java \
                ASample.java \
                IData.java \
                QuickAccess.java \
                AnalyThread.java \
                FileData.java \
                LogAnaly.java \
                LogModel.java \
                Utils.java \
                play.java
OBJECTS=        $(SOURCES:%.java=%.class)
PLAY=    $(JAVA) $(JAVA_FLAGS) play

# Uncomment to turn on debug message generation.
# TRACE=          -g

# Uncomment to turn on serialization.
# SERIAL=         -serial

# Uncomment to turn on synchronized.
# SYNC=           -sync

# Uncomment to turn on reflection.
# REFLECT=        -reflect

# Uncomment to turn on generic collections..
# GENERIC=        -generic

SMC=            java -jar ./lib/Smc.jar
SMC_FLAGS=      -java $(TRACE) $(SERIAL) $(SYNC) $(REFLECT) $(GENERIC)

CLASSPATH= .:./lib/statemap.jar:/usr/share/java/log4j-1.2.jar

JAVAC=          javac
JAVAC_FLAGS=    -g -Xlint:unchecked -classpath $(CLASSPATH)

JAVA=           java
JAVA_FLAGS=     -classpath $(CLASSPATH)

RM_F=           rm -f

#################################################################
# Rules.
#

%Context.java : %.sm
		$(SMC) $(SMC_FLAGS) $<

%_sm.dot :      %.sm
		$(SMC) -graph -glevel 1 $<

%_sm.png :      %_sm.dot
		dot -T png -o $@ $<

%_sm.html :     %.sm
		$(SMC) -table $<

all :           play

play:   $(SOURCES)
		$(JAVAC) $(JAVAC_FLAGS) $(SOURCES)

test :          $(OBJECTS)
		-$(PLAY) access.log

graph :         $(SM_SOURCES:%.sm=%_sm.dot)

png :           $(SM_SOURCES:%.sm=%_sm.png)

table :         $(SM_SOURCES:%.sm=%_sm.html)

clean :
		-$(RM_F) *.class
		-$(RM_F) *Context.java
		-$(RM_F) *_sm.dot
		-$(RM_F) *_sm.png
		-$(RM_F) *_sm.html

#
# CHANGE LOG
# $Log: Makefile,v $
# Revision 1.11  2009/03/01 18:20:38  cwrapp
# Preliminary v. 6.0.0 commit.
#
# Revision 1.10  2008/07/29 06:50:58  fperrad
# - add a target 'png'
#
# Revision 1.9  2008/05/20 18:31:11  cwrapp
# ----------------------------------------------------------------------
#
# Committing release 5.1.0.
#
# Modified Files:
# 	Makefile README.txt smc.mk tar_list.txt bin/Smc.jar
# 	examples/Ant/EX1/build.xml examples/Ant/EX2/build.xml
# 	examples/Ant/EX3/build.xml examples/Ant/EX4/build.xml
# 	examples/Ant/EX5/build.xml examples/Ant/EX6/build.xml
# 	examples/Ant/EX7/build.xml examples/Ant/EX7/src/Telephone.java
# 	examples/Java/EX1/Makefile examples/Java/EX4/Makefile
# 	examples/Java/EX5/Makefile examples/Java/EX6/Makefile
# 	examples/Java/EX7/Makefile examples/Ruby/EX1/Makefile
# 	lib/statemap.jar lib/C++/statemap.h lib/Java/Makefile
# 	lib/Php/statemap.php lib/Scala/Makefile
# 	lib/Scala/statemap.scala net/sf/smc/CODE_README.txt
# 	net/sf/smc/README.txt net/sf/smc/Smc.java
# ----------------------------------------------------------------------
#
# Revision 1.8  2008/04/24 09:29:53  fperrad
# + fix #1934494 : remove 'test' from 'all' target
#
# Revision 1.7  2008/02/04 12:35:23  fperrad
# + Exhibit options
# + add a target 'table'
#
# Revision 1.6  2006/09/17 16:26:06  cwrapp
# Initial v. 4.3.3 check-in.
#
# Revision 1.5  2005/11/07 19:34:54  cwrapp
# Changes in release 4.3.0:
# New features:
#
# + Added -reflect option for Java, C#, VB.Net and Tcl code
#   generation. When used, allows applications to query a state
#   about its supported transitions. Returns a list of transition
#   names. This feature is useful to GUI developers who want to
#   enable/disable features based on the current state. See
#   Programmer's Manual section 11: On Reflection for more
#   information.
#
# + Updated LICENSE.txt with a missing final paragraph which allows
#   MPL 1.1 covered code to work with the GNU GPL.
#
# + Added a Maven plug-in and an ant task to a new tools directory.
#   Added Eiten Suez's SMC tutorial (in PDF) to a new docs
#   directory.
#
# Fixed the following bugs:
#
# + (GraphViz) DOT file generation did not properly escape
#   double quotes appearing in transition guards. This has been
#   corrected.
#
# + A note: the SMC FAQ incorrectly stated that C/C++ generated
#   code is thread safe. This is wrong. C/C++ generated is
#   certainly *not* thread safe. Multi-threaded C/C++ applications
#   are required to synchronize access to the FSM to allow for
#   correct performance.
#
# + (Java) The generated getState() method is now public.
#
# Revision 1.4  2005/05/28 13:51:23  cwrapp
# Update Java examples 1 - 7.
#
# Revision 1.1  2004/10/30 12:00:21  charlesr
# Adding Graphviz rules.
#
# Revision 1.0  2003/12/14 19:50:47  charlesr
# Initial revision
#
