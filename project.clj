(defproject ape "0.0.1-SNAPSHOT"
  :description "Ape API"
  :url "http://github.com/ararog/ape-api"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [
    [org.clojure/clojure "1.8.0"]
    [buddy/buddy-core "0.12.0"]
    [buddy/buddy-auth "0.12.0"]
    [liberator "0.14.1"]
    [compojure "1.5.0"]
    [clj-time "0.11.0"]
    [korma "0.4.2"]
    [lobos "1.0.0-beta3"]
    [org.clojure/java.jdbc "0.3.7"]
    [org.xerial/sqlite-jdbc "3.8.11.2"]
    [ring/ring-core "1.4.0"]
    [ring/ring-json "0.4.0"]
    [ring/ring-jetty-adapter "1.4.0"]
    [log4j "1.2.15" :exclusions [javax.mail/mail
                            javax.jms/jms
                            com.sun.jdmk/jmxtools
                            com.sun.jmx/jmxri]]
  ]
  :main ape.web
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
