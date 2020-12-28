(ns version-clj.core-test
  (:require #?(:clj [clojure.test :refer [deftest are]]
               :cljs [cljs.test :refer-macros [deftest are]])
            [version-clj.core :refer [snapshot? qualified?]]))

(deftest t-snapshot
  (are [v r] (= r (boolean (snapshot? v)))
       "1.0.0"                  false
       "SNAPSHOT"               true
       "1-SNAPSHOT"             true
       "1.0-SNAPSHOT"           true
       "1.0-SNAPSHOT.2"         true
       "1.0-NOSNAPSHOT"         false))

(deftest t-qualified
  (are [v r] (= r (boolean (qualified? v)))
       "1.0.0"                  false
       "SNAPSHOT"               true
       "1-SNAPSHOT"             true
       "1.0-SNAPSHOT"           true
       "1.0-SNAPSHOT.2"         true
       "1.0-NOSNAPSHOT"         true
       "1.x.2"                  false
       "1.2y"                   true
       "1.y2"                   false
       "1.y"                    false))
