#!/bin/sh
USAGE="Usage: `basename $0` [-n] [-h] [-v] [-f]"

# parse command line arguments
CVS_QUIET="-q"
OPTIND=1
while getopts 'nhvf' OPT; do
    case "$OPT" in
	h)  echo $USAGE
            exit 0
            ;;
	v)  VERBOSE="-v"
            CVS_QUIET=""
            ;;
	n)  NO_EXPORT=1
            ;;
	f)  FORCE_RM_FLAG="-f"
            ;;
	?)  # all other characters - error
            echo $USAGE >&2
            exit 1
            ;;
    esac
done
shift `expr $OPTIND - 1`

# access additional parameters through $@ or $* as usual or using this loop:
# for PARAM; do
#    echo $PARAM
# done

continueChoice () {
    printf "$* Continue? [Y/n]"

    read -n 1 -rs choice # reading single character
    echo

    if [ "$choice" = "n" ] || [ "$choice" = "N" ] ; then
        echo "Stopping script..."
        exit 0;
    fi
}

ensureDir () {
    if [ ! -d $1 ] ; then
        mkdir -p $VERBOSE $1
    fi
}

pushd .
cd `pwd`/`dirname $0`/..
NEW_PROJECT_ROOT=`pwd`/metro
EXPORTED_RT_MASTER_ROOT=`pwd`/\_tmp-exported-rt-master
EXPORTED_RT_ROOT=`pwd`/\_tmp-exported-rt
OLD_METRO_LIB_DIR=`pwd`/wsit/lib
popd

continueChoice "New project root is set to: $NEW_PROJECT_ROOT\nExported WSIT RT MASTER root is set to: $EXPORTED_RT_MASTER_ROOT\nExported WSIT RT root is set to: $EXPORTED_RT_ROOT\n"

if [ ! -n "$NO_EXPORT" ] ; then
    if [ -e $EXPORTED_RT_MASTER_ROOT ] ; then
        rm -rf $VERBOSE $EXPORTED_RT_MASTER_ROOT
    fi

    pushd .
    cd `dirname $EXPORTED_RT_MASTER_ROOT`
    cvs -d :pserver:guest@cvs.dev.java.net:/cvs -z 9 $CVS_QUIET export -f -R -r HEAD -d `basename $EXPORTED_RT_MASTER_ROOT` wsit/wsit/rt
    popd
fi

if [ -e $EXPORTED_RT_ROOT ] ; then
    rm -rf $VERBOSE $EXPORTED_RT_ROOT
fi
cp -R $EXPORTED_RT_MASTER_ROOT $EXPORTED_RT_ROOT


if [ -e $NEW_PROJECT_ROOT ] ; then
    rm -rf $NEW_PROJECT_ROOT
fi
mkdir -p $VERBOSE $NEW_PROJECT_ROOT

source ./setup-module.sh $VERBOSE $FORCE_RM_FLAG -m $NEW_PROJECT_ROOT -p ./poms/metro-pom.xml
source ./setup-module.sh $VERBOSE $FORCE_RM_FLAG -m $NEW_PROJECT_ROOT/wsit -p ./poms/wsit-pom.xml

source ./migrate-wsit-sources.sh

ensureDir "$NEW_PROJECT_ROOT/bundles"
ensureDir "$NEW_PROJECT_ROOT/hudson"
ensureDir "$NEW_PROJECT_ROOT/legal"
ensureDir "$NEW_PROJECT_ROOT/samples"
