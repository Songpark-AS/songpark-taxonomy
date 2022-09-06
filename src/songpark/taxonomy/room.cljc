(ns songpark.taxonomy.room
  (:require [clojure.spec.alpha :as spec]
            [clojure.string :as str]
            [songpark.taxonomy.auth]
            [songpark.taxonomy.profile]
            [songpark.taxonomy.util :refer [tick?]]))

(spec/def :room/id number?)
(spec/def :room/owner :auth.user/id)
(spec/def :room/name (spec/and string?
                               #(not (str/blank? %))))
(spec/def :room/name-normalized (spec/and string?
                                          #(not (str/blank? %))))
(spec/def :room/last-jammed tick?)
(spec/def :room/last-jammers (spec/coll-of string?))

(spec/def :room/save (spec/keys :req [:room/name]))
(spec/def :room/update (spec/keys :req [:room/id
                                        :room/name]))

(spec/def :room/room (spec/keys :req [:room/id
                                      :room/name
                                      :room/name-normalized]
                                :opt [:room/owner
                                      :room/last-jammed
                                      :room/last-jammers]))
(spec/def :room/rooms (spec/coll-of :room/room))

(spec/def :room/jammer (spec/keys :req [:auth.user/id
                                        :profile/name
                                        :profile/position]
                                  :opt [:profile/image-url]))
(spec/def :room/jammers (spec/coll-of :room/jammer))
(spec/def :room.jammer/id :auth.user/id)

(spec/def :room/session (spec/keys :req [:room/id]))
(spec/def :room.session/host (spec/keys :req [:room/id]))
(spec/def :room.session/hosted (spec/keys :req [:room/id]))
(spec/def :room.session/knock (spec/keys :req [:room/name]))
(spec/def :room.session/knocked (spec/keys :req [:room/id
                                                 :room/name]))
(spec/def :room.session/accept (spec/keys :req [:room/id
                                                :room/jammer]))
(spec/def :room.session/decline (spec/keys :req [:room/id
                                                 :room/jammer]))
(spec/def :room.session/leave (spec/keys :req [:room/id]))
(spec/def :room.session/remove (spec/keys :req [:room/id
                                                :room.jammer/id]))
(spec/def :room.session/close (spec/keys :req [:room/id]))
