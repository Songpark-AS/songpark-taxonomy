(ns songpark.taxonomy.room
  (:require [clojure.spec.alpha :as spec]
            [clojure.string :as str]
            [songpark.taxonomy.auth]
            [songpark.taxonomy.jam]
            [songpark.taxonomy.profile]
            [songpark.taxonomy.util :refer [tick?]]))

(spec/def :room/id number?)
(spec/def :room/owner :auth.user/id)
(spec/def :room/name (spec/and string?
                               #(not (str/blank? %))))
(spec/def :room/name-normalized (spec/and string?
                                          #(not (str/blank? %))))
(spec/def :room/last-jammed tick?)
(spec/def :room/jammer-names (spec/coll-of string?))

(spec/def :room/save (spec/keys :req [:room/name]))
(spec/def :room/update (spec/keys :req [:room/id
                                        :room/name]))

(spec/def :room/room (spec/keys :req [:room/id
                                      :room/name
                                      :room/name-normalized]
                                :opt [:room/owner]))
(spec/def :room/rooms (spec/coll-of :room/room))

(spec/def :room/jammer (spec/keys :req [:auth.user/id
                                        :profile/name
                                        :profile/position
                                        :jammer/status]
                                  :opt [:profile/image-url]))
(spec/def :room/jammers (spec/map-of :auth.user/id :room/jammer))

(spec/def :room/jam (spec/keys :req [:room/id
                                     :room/owner
                                     :room/name
                                     :room/name-normalized
                                     :room/jammers]))
(spec/def :room/jams (spec/coll-of :room/jam))

(spec/def :room.jam/interlude (spec/keys :req [:room/id
                                               :room/name
                                               :room/last-jammed
                                               :room/jammer-names]))
(spec/def :room.jam/history (spec/coll-of :room.jam/interlude))


(spec/def :room.jam/host (spec/keys :req [:room/id]))
(spec/def :room.jam/hosted :room/jam)
(spec/def :room.jam/knock (spec/keys :req [:room/name]
                                     :opt [:room/name-normalized]))
(spec/def :room.jam/knocked (spec/keys :req [:room/id
                                             :room/name]))
(spec/def :room.jam/accept (spec/keys :req [:room/id
                                            :auth.user/id]))
(spec/def :room.jam/accepted (spec/keys :req [:room/id
                                              :auth.user/id]))
(spec/def :room.jam/decline (spec/keys :req [:room/id
                                             :auth.user/id]))
(spec/def :room.jam/declined (spec/keys :req [:room/id
                                              :auth.user/id]))
(spec/def :room.jam/leave (spec/keys :req [:room/id]))
(spec/def :room.jam/remove (spec/keys :req [:room/id
                                            :auth.user/id]))
(spec/def :room.jam/removed (spec/keys :req [:room/id
                                             :auth.user/id]))
(spec/def :room.jam/close (spec/keys :req [:room/id]))
