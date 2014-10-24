#!/bin/bash
# --------------------------------------------------
#
#       Title: tests.sh
# Description: Script Testes Entrega Final IST PO12
#      Author: Ricardo Sequeira
#
#        Name: tests
#        File: tests.sh
#     Created: November 18, 2012
#       Usage: ./tests.sh
#
# --------------------------------------------------


# Directorio onde se encontram os testes da entrega intermedia
# disponiveis em https://fenix.ist.utl.pt/disciplinas/po-4/2012-2013/1-semestre/projecto/desenvolvimento-do-projecto/testes-para-a-entrega-final
TESTSDIR='/home/oops/tests-ef-201211091438'

# Directorio base do projecto
PROJDIR='/home/oops/project/'

# Directorio onde se encontra o material de apoio (po-uilib, rest-textui-manager-support, rest-textui-outlet-support)
SUPPORTDIR='/usr/share/java'



TEMPDIR='/tmp'


SUCCESS=0
FAIL=0
STRRESULTS=""

#exec > >(tee $TEMPDIR/tests.res)

echo "Compiling..."
cd $PROJDIR
$(make)


if [ -f $PROJDIR/rest-core/rest-core.jar ]
  then
    echo "-- successfully generated 'rest-core.jar'".
  else
    echo "!! default makefile rule did not produce 'rest-core.jar'"
fi

if [ -f $PROJDIR/rest-textui-manager/rest-textui-manager.jar ]
  then
    echo "-- successfully generated 'rest-textui-manager.jar'".
  else
    echo "!! default makefile rule did not produce 'rest-textui-manager.jar'"
fi

if [ -f $PROJDIR/rest-textui-outlet/rest-textui-outlet.jar ]
  then
    echo "-- successfully generated 'rest-textui-outlet.jar'".
  else
    echo "!! default makefile rule did not produce 'rest-textui-outlet.jar'"
fi


for file in $TESTSDIR/*.in
do
    filename=$(basename $file)
    filename=${filename%%.*}
    if [ "${filename:10:1}" == "O" ]
    then
      if [ -f $TESTSDIR/$filename.import ]
      then
	java -DImport=$TESTSDIR/$filename.import -Din=$TESTSDIR/$filename.in -Dout=$TEMPDIR/test.out -cp $PROJDIR/rest-textui-manager/rest-textui-manager.jar:$PROJDIR/rest-textui-outlet/rest-textui-outlet.jar:$PROJDIR/rest-core/rest-core.jar:$SUPPORTDIR/po-uilib.jar:$SUPPORTDIR/rest-textui-manager-support.jar:$SUPPORTDIR/rest-textui-outlet-support.jar rest.textui.Outlet
      else
	java -Din=$TESTSDIR/$filename.in -Dout=$TEMPDIR/test.out -cp $PROJDIR/rest-textui-manager/rest-textui-manager.jar:$PROJDIR/rest-textui-outlet/rest-textui-outlet.jar:$PROJDIR/rest-core/rest-core.jar:$SUPPORTDIR/po-uilib.jar:$SUPPORTDIR/rest-textui-manager-support.jar:/usr/share/java/rest-textui-outlet-support.jar rest.textui.Outlet
      fi
    else
      if [ -f $TESTSDIR/$filename.import ]
      then
	java -DImport=$TESTSDIR/$filename.import -Din=$TESTSDIR/$filename.in -Dout=$TEMPDIR/test.out -cp rest-textui-manager/rest-textui-manager.jar:$PROJDIR/rest-core/rest-core.jar:$SUPPORTDIR/po-uilib.jar:$SUPPORTDIR/rest-textui-manager-support.jar:$SUPPORTDIR/rest-textui-outlet-support.jar rest.textui.Manager
      else
	java -Din=$TESTSDIR/$filename.in -Dout=$TEMPDIR/test.out -cp rest-textui-manager/rest-textui-manager.jar:$PROJDIR/rest-core/rest-core.jar:$SUPPORTDIR/po-uilib.jar:$SUPPORTDIR/rest-textui-manager-support.jar:/usr/share/java/rest-textui-outlet-support.jar rest.textui.Manager
      fi
    fi
    

    echo "====================$filename===================="
    if diff -w $TESTSDIR/expected/$filename.out $TEMPDIR/test.out; then
      
      echo "TEST PASSED!"
      SUCCESS=$[SUCCESS+1]
      STRRESULTS="$STRRESULTS$filename - OK\n"
    else
      echo "output differs from expected"
      FAIL=$[FAIL+1]
      STRRESULTS="$STRRESULTS$filename - Errado, Burro do Caralho!\n"
    fi
done
echo ""
echo "=====================TEST RESULTS====================="
echo -e $STRRESULTS
echo "SUCCESS: $SUCCESS"
echo "FAIL: $FAIL"


echo ""
echo "Generated '$TEMPDIR/tests.res'"