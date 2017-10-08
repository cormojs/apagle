# Apagle - a Mastodon compatible social networking mock

## next goal is:
To act as a mastodon remote instance.
Now focusing to implement protocols which are used in Mastodon.

## todo
* Write English README.md


## What can it do?
* `/.well-known/host-meta`からHost Meta(RFC6415)を吐く
* `/.well-known/webfinger?resource={uri}`からWebfinger(RFC7033)を吐く
* `/users/:username`からPerson型のActivityPubオブジェクトを吐く

## How config
1. `cd resources/com/twitter/tunables/apagle`
2. `cp instances.json.example instances.json`
3. Edit `instances.json`