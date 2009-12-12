#!/bin/sh
USAGE="Usage: setup-module.sh [-hvfn] [-m <module-root>] [-p <pom-template>]"

# we want at least one parameter (it may be a flag or an argument)
if [ $# -le 1 ]; then
	echo $USAGE >&2
	exit 1
fi

# parse command line arguments
OPTIND=1
while getopts 'hvfnm:p:' OPT; do
    echo "$OPT"

    case "$OPT" in
	h)  echo $USAGE
            exit 0
            ;;
	v)  VERBOSE="-v"
            ;;
	f)  FORCE_RM_FLAG="-f"
            ;;
	n)  NO_SOURCE_DIR_FLAG="-n"
            ;;
	m)  MODULE_ROOT=$OPTARG
            ;;
        p)  POM_TEMPLATE=$OPTARG
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


if [ -z "$MODULE_ROOT" ] ; then
    echo "No module root specified" >&2
    echo $USAGE >&2
    exit 1
fi

if [ -z "$NO_SOURCE_DIR_FLAG" ] ; then
    echo "here"
    if [ -d $MODULE_ROOT/src ] ; then
        rm -ir $FORCE_RM_FLAG $VERBOSE -r $MODULE_ROOT/src
    fi

    mkdir -p $VERBOSE $MODULE_ROOT/src/main/java
    mkdir -p $VERBOSE $MODULE_ROOT/src/test/java
fi


if [ -z "$POM_TEMPLATE" ] ; then
    echo "No pom.xml template specified"
    return 0
else
    cp $VERBOSE $POM_TEMPLATE $MODULE_ROOT/pom.xml
fi


