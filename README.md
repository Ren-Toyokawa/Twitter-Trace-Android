Twitter-Trace-Android
# これは何か
Androidアプリ開発の学習のためにTwitterアプリのトレースをするレポジトリとなります。

下記を目的としています
- ポートフォリオ
- Androidの知識で不足している部分を補う

同様の目的でiOS, iPadOS版も作成中です。

# レポジトリの構成
## アーキテクチャ

### 参考にしたページ

- Android developers
  - https://developer.android.com/jetpack/guide?hl=ja
  - https://developer.android.com/jetpack/guide/ui-layer?hl=ja
  - https://developer.android.com/jetpack/guide/domain-layer?hl=ja
  - https://developer.android.com/jetpack/guide/data-layer?hl=ja
- Google のサンプルプロジェクト(Google I/Oのスケジュールアプリ)
  - https://github.com/google/iosched

### マルチモジュール

検討しましたが
- 現状メリットが薄い
- まず単一モジュールで作り分割する方が学習コストが低そう

といった理由で今回は採用していません。

### アーキテクチャイメージ

<img src="/images/Architecture.png" width="250">

Android Developersのページを参考に自分で作りました

## ディレクトリ構造

---

### ui
名前の通りUI部分を担います  
[Android Developers](https://developer.android.com/jetpack/guide/ui-layer?hl=ja)の言葉を引用すると
```
UI レイヤは、アプリデータの変更を UI が提示できる形式に変換して表示するパイプラインです。
```


下記のような構成になっています。
- 機能
  - 機能Activity
  - 機能Fragment
  - 機能ViewModel

ViewはXMLを用いて実装します。  
Jetpack Compose を使いたいのですが、まだ公式が推奨していないため今回は使用していません。  
今後追加する可能性はあります。

---

### domain
#### usecase
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

# その他
- Kotlin DSLを使用している理由としては実験的な意味合いが強いです。
  Kotlin DSLを使用するとビルドが遅くなる可能性があることは認識しています。(参考: [https://developer.android.com/studio/build/migrate-to-kts?hl=ja])
