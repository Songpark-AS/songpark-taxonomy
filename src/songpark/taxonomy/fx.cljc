(ns songpark.taxonomy.fx
  (:require [clojure.spec.alpha :as spec]))


(spec/def :fx/type keyword?)

;; panorama
(spec/def :fx.input1/pan number?)
(spec/def :fx.input2/pan number?)
;; gain
(spec/def :fx.input1/gain number?)
(spec/def :fx.input2/gain number?)

;; gate
(spec/def :fx.input1.gate/switch boolean?)
(spec/def :fx.input2.gate/switch boolean?)
(spec/def :fx.input1.gate/threshold number?)
(spec/def :fx.input2.gate/threshold number?)
(spec/def :fx.input1.gate/attack number?)
(spec/def :fx.input2.gate/attack number?)
(spec/def :fx.input1.gate/release number?)
(spec/def :fx.input2.gate/release number?)

(spec/def :fx.input1/gate (spec/keys :req [:fx.input1.gate/threshold
                                           :fx.input1.gate/attack
                                           :fx.input1.gate/release]
                                     :opt [:fx.input1.gate/switch]))

(spec/def :fx.input2/gate (spec/keys :req [:fx.input2.gate/threshold
                                           :fx.input2.gate/attack
                                           :fx.input2.gate/release]
                                     :opt [:fx.input2.gate/switch]))

;; reverb
(spec/def :fx.input1.reverb/switch boolean?)
(spec/def :fx.input2.reverb/switch boolean?)
(spec/def :fx.input1.reverb/mix number?)
(spec/def :fx.input2.reverb/mix number?)
(spec/def :fx.input1.reverb/damp number?)
(spec/def :fx.input2.reverb/damp number?)
(spec/def :fx.input1.reverb/room-size number?)
(spec/def :fx.input2.reverb/room-size number?)

(spec/def :fx.input1/reverb (spec/keys :req [:fx.input1.reverb/mix
                                             :fx.input1.reverb/damp
                                             :fx.input1.reverb/room-size]
                                       :opt [:fx.input1.reverb/switch]))

(spec/def :fx.input2/reverb (spec/keys :req [:fx.input2.reverb/mix
                                             :fx.input2.reverb/damp
                                             :fx.input2.reverb/room-size]
                                       :opt [:fx.input2.reverb/switch]))

;; amplify
(spec/def :fx.input1.amplify/switch boolean?)
(spec/def :fx.input2.amplify/switch boolean?)
(spec/def :fx.input1.amplify/drive number?)
(spec/def :fx.input2.amplify/drive number?)
(spec/def :fx.input1.amplify/tone number?)
(spec/def :fx.input2.amplify/tone number?)

(spec/def :fx.input1/amplify (spec/keys :req [:fx.input1.amplify/drive
                                              :fx.input1.amplify/tone]
                                        :opt [:fx.input1.amplify/switch]))

(spec/def :fx.input2/amplify (spec/keys :req [:fx.input2.amplify/drive
                                              :fx.input2.amplify/tone]
                                        :opt [:fx.input2.amplify/switch]))

;; equalizer
(spec/def :fx.input1.equalizer/switch boolean?)
(spec/def :fx.input2.equalizer/switch boolean?)
(spec/def :fx.input1.equalizer/low number?)
(spec/def :fx.input2.equalizer/low number?)
(spec/def :fx.input1.equalizer/medium-low number?)
(spec/def :fx.input2.equalizer/medium-low number?)
(spec/def :fx.input1.equalizer/medium-high number?)
(spec/def :fx.input2.equalizer/medium-high number?)
(spec/def :fx.input1.equalizer/high number?)
(spec/def :fx.input2.equalizer/high number?)

(spec/def :fx.input1/equalizer (spec/keys :req [:fx.input1.equalizer/low
                                                :fx.input1.equalizer/medium-low
                                                :fx.input1.equalizer/medium-high
                                                :fx.input1.equalizer/high]
                                          :opt [:fx.input1.equalizer/switch]))

(spec/def :fx.input2/equalizer (spec/keys :req [:fx.input2.equalizer/low
                                                :fx.input2.equalizer/medium-low
                                                :fx.input2.equalizer/medium-high
                                                :fx.input2.equalizer/high]
                                          :opt [:fx.input2.equalizer/switch]))

;; echo
(spec/def :fx.input1.echo/switch boolean?)
(spec/def :fx.input2.echo/switch boolean?)
(spec/def :fx.input1.echo/delay-time number?)
(spec/def :fx.input2.echo/delay-time number?)
(spec/def :fx.input1.echo/level number?)
(spec/def :fx.input2.echo/level number?)

(spec/def :fx.input1/echo (spec/keys :req [:fx.input1.echo/delay-time
                                           :fx.input1.echo/level]
                                     :opt [:fx.input1.echo/switch]))


(spec/def :fx.input2/echo (spec/keys :req [:fx.input2.echo/delay-time
                                           :fx.input2.echo/level]
                                     :opt [:fx.input2.echo/switch]))

;; compressor
(spec/def :fx.input1.compressor/switch boolean?)
(spec/def :fx.input2.compressor/switch boolean?)
(spec/def :fx.input1.compressor/threshold number?)
(spec/def :fx.input2.compressor/threshold number?)
(spec/def :fx.input1.compressor/ratio number?)
(spec/def :fx.input2.compressor/ratio number?)
(spec/def :fx.input1.compressor/attack number?)
(spec/def :fx.input2.compressor/attack number?)
(spec/def :fx.input1.compressor/release number?)
(spec/def :fx.input2.compressor/release number?)

(spec/def :fx.input1/compressor (spec/keys :req [:fx.input1.compressor/threshold
                                                 :fx.input1.compressor/ratio
                                                 :fx.input1.compressor/attack
                                                 :fx.input1.compressor/release]
                                           :opt [:fx.input1.compressor/switch]))

(spec/def :fx.input2/compressor (spec/keys :req [:fx.input2.compressor/threshold
                                                 :fx.input2.compressor/ratio
                                                 :fx.input2.compressor/attack
                                                 :fx.input2.compressor/release]
                                           :opt [:fx.input2.compressor/switch]))


;; presets

(spec/def :fx/preset (spec/and
                      (spec/merge (spec/keys :req [:fx/type])
                                  (spec/or :fx.input1/gate :fx.input1/gate
                                           :fx.input2/gate :fx.input2/gate
                                           :fx.input1/reverb :fx.input1/reverb
                                           :fx.input2/reverb :fx.input2/reverb
                                           :fx.input1/amplify :fx.input1/amplify
                                           :fx.input2/amplify :fx.input2/amplify
                                           :fx.input1/equalizer :fx.input1/equalizer
                                           :fx.input2/equalizer :fx.input2/equalizer
                                           :fx.input1/echo :fx.input1/echo
                                           :fx.input2/echo :fx.input2/echo
                                           :fx.input1/compressor :fx.input1/compressor
                                           :fx.input2/compressor :fx.input2/compressor))
                      (fn [{fx-type :fx/type :as data}]
                        (cond (and (= fx-type :fx.input1/gate)
                                   (contains? data :fx.input1.gate/threshold))
                              true

                              (and (= fx-type :fx.input2/gate)
                                   (contains? data :fx.input2.gate/threshold))
                              true

                              (and (= fx-type :fx.input1/reverb)
                                   (contains? data :fx.input1.reverb/mix))
                              true

                              (and (= fx-type :fx.input2/reverb)
                                   (contains? data :fx.input2.reverb/mix))
                              true

                              (and (= fx-type :fx.input1/amplify)
                                   (contains? data :fx.input1.amplify/drive))
                              true

                              (and (= fx-type :fx.input2/amplify)
                                   (contains? data :fx.input2.amplify/drive))
                              true

                              (and (= fx-type :fx.input1/equalizer)
                                   (contains? data :fx.input1.equalizer/low))
                              true

                              (and (= fx-type :fx.input2/equalizer)
                                   (contains? data :fx.input2.equalizer/low))
                              true

                              (and (= fx-type :fx.input1/echo)
                                   (contains? data :fx.input1.echo/delay-time))
                              true

                              (and (= fx-type :fx.input2/echo)
                                   (contains? data :fx.input2.echo/delay-time))
                              true

                              (and (= fx-type :fx.input1/compressor)
                                   (contains? data :fx.input1.compressor/threshold))
                              true

                              (and (= fx-type :fx.input2/compressor)
                                   (contains? data :fx.input2.compressor/threshold))
                              true

                              :else
                              false))))


(spec/def :fx/presets (spec/coll-of :fx/preset))
