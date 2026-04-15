module javaxx {
    requires java.net.http;
    requires jdk.httpserver;
    requires java.scripting;

    exports javaxx.newfeatures.localvariabletypereference;
    exports javaxx.newfeatures.recordclasses;
    exports javaxx.newfeatures.textblocks;
    exports javaxx.newfeatures.virtualthreads;
    exports javaxx.newfeatures.privatemethodsinterfaces;
    exports javaxx.newfeatures.stringmethods;
    exports javaxx.newfeatures.httpclient;
    exports javaxx.newfeatures.switchexpressions;
    exports javaxx.newfeatures.instanceofpatternmatching;
    exports javaxx.newfeatures.sealedclasses;
    exports javaxx.newfeatures.patternmatchingswitch;
    exports javaxx.newfeatures.simplewebserver;
    exports javaxx.newfeatures.stringtemplates;
    exports javaxx.newfeatures.sequencedcollections;
}