Twitter-Trace-Android
# これは何か
Androidアプリ開発の学習のためにTwitterアプリのトレースをするレポジトリとなります。

下記を目的としています
- ポートフォリオ
- Androidの知識で不足している部分を補う

同様の目的でiOS,iPadOS版も作成中です。

# レポジトリの構成
## アーキテクチャ
MVVMを採用しています
OSSのプロジェクトを参考にしていますが、ディレクトリの分け方などが正しくはないかもしれません(気づき次第随時改善していく予定です)。

マルチモジュールも検討しましたが
- 現状メリットが薄い
- まず単一モジュールで作り分割する方が学習コストが低そう

といった理由で今回は採用していません。

## ディレクトリ
### ui
下記のような構成になっています。
- 機能
  - 機能Activity
  - 機能Fragment
  - 機能ViewModel

### data.usecase
ViewModel と Repositryの窓口です。
ViewModelはUsecaseを介さないとデータへアクセスできません

### data.repository
UseCase毎に必要なdatasourceを保持します。
UseCaseからのリクエストに応じ、必要なデータを返却します。

### data.datasource
Dataを取得する大元になります。
APIのエンドポイント, Firestore等を内包しています。

### data.model
アプリ内で使用する Dataの構造を定義しています。

# 処理の流れ
TODO: UMLにする
TODO: もっと実際の処理をイメージしやすいように

1. Fragment
2. ViewModel
3. Use Case
4. Repository
5. Datasource

# その他
- Kotlin DSLを使用している理由としては実験的な意味合いが強いです。
  Kotlin DSLを使用するとビルドが遅くなる可能性があることは認識しています。(参考: [https://developer.android.com/studio/build/migrate-to-kts?hl=ja])
  

