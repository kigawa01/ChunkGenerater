# ChunkGenerator

スパゲッティ

最新だとなんかバグる

## Requirement

* spigot
* world edit

# Author

* kigawa
    * kigawa.8390@gmail.com


## Command

* /generator \<subcommand>

## Specification

* regenerateコマンドで指定したチャンクを再生成できます
* 毎時間00分にそれぞれの範囲ごとにコンフィグの確率で再生成します
* setregionコマンドでworldEditで指定した範囲が含まれるチャンクを指定します(複数可)
* 再生成時にチャンク内にいたプレーヤーはオンライン・オフライン問わずにspawnポイントにテレポートします
* チャンクの再生成はそれぞれの範囲ごとに一つのチャンクにつき1tickずらして生成します


# Version

### 例: 9.1a
* **9.1a**
  * プラグインのバージョン
  * **9**: メジャー
  * **1**: マイナー
  * **a**: プラグインのバージョン アルファ(a)/ベータ(b)/推奨(R)

# making



## ToDo

* [x] チャンク再生成
* [x] グループ
* [x] 確率
* [x] スケジュール
* [x] oflinePlayer
* [x] release 1.0a

## about

