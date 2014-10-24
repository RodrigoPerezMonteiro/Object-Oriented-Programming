JAVADIR=/usr/share/java
JARNAME=rest-textui-outlet

all:
	(cd src; javac -cp $(JAVADIR)/po-uilib.jar:$(JAVADIR)/rest-textui-outlet-support.jar:$(JAVADIR)/rest-core.jar -encoding UTF-8 `find . -name \*.java`)
	(cd src; jar cvf ../$(JARNAME).jar `find . -name \*.class -o -name \*.java`)

clean:
	$(RM) $(JARNAME).jar `find . -name \*.class`

install:
	/bin/cp $(JARNAME).jar $(JAVADIR)
