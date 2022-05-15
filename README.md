Twitter-Trace-Android
# 概要
実務で半年ほど Androidアプリの作成、保守運用を担当しているのですが、元々サーバーサイドを担当していたため
- Androidの基礎知識
- 最新のAndroid開発のキャッチアップ

が不足していると感じており、それらを学習するためのレポジトリとなっています。  
このレポジトリを育てていき、転職や案件獲得の際のポートフォリオにもできたら良いなと思っています。

現在個人開発のアプリに注力しているため、更新頻度は少なくなっています

# 使用技術
- Kotlin
- Jetpack Compose
- Navigation Component
- Hilt

# レポジトリの構成
## アーキテクチャ
- Android Developers で推奨されている UI-Domain-Data で形成されるアーキテクチャを採用しています
  - https://developer.android.com/jetpack/guide?hl=ja
  
### アーキテクチャイメージ
<img src="/images/Architecture.png" width="250">

## ディレクトリ構造

---

### ui
名前の通りUI部分を担います  
[Android Developers](https://developer.android.com/jetpack/guide/ui-layer?hl=ja)の言葉を引用すると
```
UIレイヤはアプリデータの変更をUIが提示できる形式に変換して表示するパイプラインです。
```

下記のような構成になっています。
- 機能
  - 機能Screen
  - 機能ViewModel

---

### domain(現在は存在ていません)
#### usecase
※現在は存在していませんが、将来的に作成する予定です  
ViewModel と Repositryの仲介をします。  
ViewModelはUsecaseを介さないとデータへアクセスできません

### data
#### repository
UseCase毎に必要なdatasourceを保持します。  
UseCaseからのリクエストに応じ、必要なデータを返却します。

#### datasources
Dataを取得する大元になります。
API, Firestore等を内包しています。

---

### model
アプリ内で使用する Dataの構造を定義しています。  
このディレクトリは全てのレイヤーから参照される想定です

---

### 参考にしたページ

- Android developers
  - https://developer.android.com/jetpack/guide?hl=ja
  - https://developer.android.com/jetpack/guide/ui-layer?hl=ja
  - https://developer.android.com/jetpack/guide/domain-layer?hl=ja
  - https://developer.android.com/jetpack/guide/data-layer?hl=ja
- Google のサンプルプロジェクト(Google I/Oのスケジュールアプリ)
  - https://github.com/google/iosched
- Official Jetpack Compose samples.
  - https://github.com/android/compose-samples

# その他
- Kotlin DSLを使用している理由としては実験的な意味合いが強いです。  
  Kotlin DSLを使用するとビルドが遅くなる可能性があることは認識しています。(参考: [https://developer.android.com/studio/build/migrate-to-kts?hl=ja])
