### 이 README.md는 Taehwan 님의 ['안드로이드 Compose Scaffold, TopAppBar 활용한 개발'](https://thdev.tech/android/2023/01/25/Android-Compose-Scaffold/) 글을 옮겨 놓았습니다.
Android Compose에서는 어떠한 컴포넌트를 최상위로 두고 작업하는 것이 좋을까?  

필자의 경우 Compose 시작점 코드는 아래와 같다.  
```kotlin
class SampleActivity {

  fun onCreate() {
    setContent { // Compose 활용 시작
      SampleTheme { // 기본 Theme 정의
        CompositionLocalProvider(options) { // 내부에서 활용할 CompositionLocal 이 있다면 등록
          Scaffold { // 머트리얼을 따르는 기본 틀
            // 여기에서 View 처리
          }
        }
      }
    }
  }
}
```
Theme를 기본으로 적용하고, 여기에 CompositionLocalProvider를 필요에 따라 추가로 활용하고 있다.

그런 다음 Scaffold를 활용해 Material을 따르는 기본 틀을 활용하고 있다.



이 글에서 알아볼 내용
Material의 구조를 가볍게 살펴보자
Material의 Scaffold를 알아본다
TopAppBar를 알아본다.


Scaffold를 알아보기 전에 Compose 구조를 살짝 보자
Compose의 Theme는 Material 2, Material 3로 구분하고 있다.

우선 Compose에서 제공하는 디펜던시는 아래와 같다.

아래 설명에도 나와있지만 Material 3와 2는 선택할 수 있고, foundation 역시 선택할 수 있다고 표기하고 있다.
```kotlin
// Choose one of the following:
// Material Design 3
implementation("androidx.compose.material3:material3")
// or Material Design 2
implementation("androidx.compose.material:material")
// or skip Material Design and build directly on top of foundational components
implementation("androidx.compose.foundation:foundation")
// or only import the main APIs for the underlying toolkit systems,
// such as input and measurement/layout
implementation("androidx.compose.ui:ui")
```

왜 그런지는 내부 코드를 조금 살펴보고, 어떤 식으로 구분되어 있는지 파악해 보면 이유를 알 수 있는데, 각각이 어떤 식으로 다루고 있는지를 조금 살펴보고 넘어가겠다.

참고로 모든 Material은 foundation/layout을 기본으로 바라보고 있다. 결국 직접 Material과 같은 시스템을 만든다고 하면 foundation/layout을 바라보고, 이를 활용하면 된다는 이야기다.

Material2/3에서 제공하는 Components
Material에서 제공하는 Components는 머트리얼 사이트에 잘 나와있으니 전체는 아래 사이트를 참고하자.

Material
Material 3
몇 가지 주요 구성요소를 적어보면

Surface : Material을 따르는 가장 기본적인 Content 영역
Scaffold : Material Theme를 활용한 기본 틀이면 아래 UI들을 포함할 수 있다.
Text : 텍스트를 출력하는 영역
TextField : 텍스트 입력을 위한 영역
CheckBox : Canvas를 활용해 직접 그린다.
Switch


Foundation 영역
Box : Material Surface에서 Box를 매핑하여 컨텐츠 영역을 확보한다.
Row : Layout을 활용해 표현한다. LinearLayout의 horizontal에 해당한다. Table row 동일
Column : Layout을 활용해 표현한다. LinearLayout의 vertical에 해당한다. Table column 동일
BasicText : 머트리얼에서 Text가 이를 매핑하여 활용한다.
BasicTextField : 머트리얼에서 TextField가 이를 매핑하여 활용한다.
Canvas : 직접 View를 draw 하고 싶다면 이 영역을 활용한다.
Column과 Row만 있어도 Compose에서는 대부분의 View를 그릴 수 있다. 어차피 성능적인 이점인 ConstraintLayout을 활용할 이유도 Compose에서는 없다. 예외 케이스가 아니라면 말이다.



UI 영역
Layout : Foundation과 Material 영역에서 이를 활용하여 View를 그리게 된다.


정리하면
왜 선택적 적용이 가능한지는 이러한 구조를 보면 잘 알 수 있다.

난 Layout 위에 직접 모든 걸 다 그리겠다. : layout만 활용하는 것이다.
난 Foundation에서 제공하는 기본 틀을 따르면서 작업하겠다. : foundation과 layout을 선택한다.
난 Material 기반 작업하겠다. : Material 버전 + foundation + layout을 선택한다.
가장 쉬운 선택은 있는 기능을 잘 활용하면서 확장도 해보고, 수정하는 형태일 것이다. 그럼 3 안을 선택할 수 있다.

어차피 구글에서 가이드 하는 건 Material 기반의 UI 시스템을 말하는 것이니, 자연스럽게 우리가 접하는 것 역시 Material 기반의 UI 컴포넌트들이다.



Material Scaffold
Material을 활용한다면 Scaffold를 활용하는 편이 좋다. Scaffold에서는 아래와 같은 화면 구성을 기본으로 제공한다.
![image_01](https://github.com/ksjmgrkks/Jetpack-Compose-Study/assets/76638683/420a0efa-686d-44c0-aab8-8a35fdb6f8d3)

Scaffold의 구조는 Material 2와 Material 3에서 조금 다른데, 이 글을 작성하는 시점에 Material 3에는 아직 BottomSheet는 아직 계획 중이다.



Material 2 Scaffold
머트리얼 2에는 크게 2가지의 Scaffold를 제공하고 있다.(그 외에는 문서 참고)

기본 Scaffold와 BottomSheetScaffold를 제공하고 있는데, 동일 선상 BottomSheet가 필요하다면 BottomSheetScaffold를 활용할 수 있다.

Scaffold에서 제공하는 내용은 아래와 같다.

topBar : TopAppBar를 활용한 Toolbar 처리
bottomBar : 하단 메뉴를 필요로 하는 경우 이를 활용한다.
snackbarHost : Snackbar 영역을 기본으로 제공한다.(이 영역을 활용하지 않고도 활용 가능하다.)
floatingActionButton : 플로팅 버튼을 기본 제공한다.
drawerContent : 왼쪽 drawer 메뉴를 제공한다.
BottomSheetScaffold는 위의 Scaffold에 BottomSheet 용 옵션을 추가로 제공한다.

bottomSheetScaffold : 컨텐츠 영역에 drag 가능한 BottomSheet를 제공한다.
유튜브나 구글 뮤직에서 이를 활용한 걸로 볼 수 있다.
최소 높이를 가진다.


Material 3 Scaffold
Material 3 Scaffold는 많은 기능이 축소되었다.

아직 BottomSheet를 제공하지 않으며, drawerContent 영역도 기본으로 제공하지 않는다. 별도의 ModalNavigationDrawer를 활용해야 한다.

topBar : TopAppBar를 활용한 Toolbar 처리
bottomBar : 하단 메뉴를 필요로 하는 경우 이를 활용한다.
snackbarHost : Snackbar 영역을 기본으로 제공한다.(이 영역을 활용하지 않고도 활용 가능하다.)
floatingActionButton : 플로팅 버튼을 기본 제공한다.
내부 BottomSheet 영역을 필요로 한다면 결국 Material 2도 함께 활용해야 한다. 사용성에 따라 선택적 활용이 가능하니 적합한 방식으로 활용하면 되겠다.

만약 dialog를 활용해 직접 구현한 바텀 시트가 필요하다면 아래 코드를 참고해도 좋을 것 같다.

홀릭스 - BottomSheetDialog



TopAppBar
Scaffold를 활용하면 TopAppBar도 자연스럽게 활용한다.

TopAppBar 구성은 왼쪽 Navigation, Title 영역, Actions 영역으로 나뉜다.
![image_02](https://github.com/ksjmgrkks/Jetpack-Compose-Study/assets/76638683/9dd7d2c3-a8ac-4935-bf95-09a1b7b33c98)

TopAppBar도 Material 2와 Material 3에서 조금 다르게 구성하고 있는데 각각을 살펴보자.



Material 2 TopAppBar
Material 2에서는 TopAppBar를 크게 2가지 제공한다.

정해진 구성을 통해 제공하는 경우 : Title, navigationIcon, actions 영역 제공
내부를 Custom 할 수 있도록 제공하는 경우 : content 영역 만 제공


Material 3 TopAppBar
material 3 TopAppBar는 종류가 많아졌지만, 내부 Custom을 기본으로 제공하지는 않는다. 대신 scrollBehavior를 기본으로 제공하여, 스크롤 상태에 따른 TopAppBar 처리가 가능하다.

기본 TopAppBar : Material 2와 동일
CenterAlignedTopAppBar : 타이틀을 가운데 정렬하고 싶다면 이를 활용하면 편하다.
MediumTopAppBar
LargeTopAppBar


BottomAppBar
Bottom 영역에 AppBar를 두는 것도 가능하다. 이 영역에는 title을 포함하지는 않는다.
![image_03](https://github.com/ksjmgrkks/Jetpack-Compose-Study/assets/76638683/b1f18024-bc1e-48bd-aa97-cda9bce2b260)
BottomAppBar는 Material 2와 Material 3에서 차이가 없다.

두 버전 모두 내부를 Custom 할 수도 있다.



마무리
UI 디펜던시를 선택할 때는 꼭 Material 디자인을 선택할 필요는 없다. 디자인 시스템을 만들다 보면 자연스럽게 Material 영역을 따르지 못할 경우도 있는데, 사실 내부 코드는 참고할 수 있으니 이를 활용하는 것도 좋은 방법이다. 그래서 굳이 Material을 따르지 않을 필요도 없다.

Foundation만 활용해 우리만의 디자인 시스템을 만드는 것 역시 쉬운건 아니다. 그냥 Material에서 제공하는 영역을 잘 알고, 이를 매핑하는 편이 더 좋은 선택이다.

아주 가까운 곳에 참고할 코드를 잔뜩 두는 것도 역시 좋은 방법이다.

Surface의 경우도 내부는 Box를 그대로 활용하고 있다.
```kotlin
@Composable
@NonRestartableComposable
fun Surface(
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape,
    color: Color = MaterialTheme.colorScheme.surface,
    contentColor: Color = contentColorFor(color),
    tonalElevation: Dp = 0.dp,
    shadowElevation: Dp = 0.dp,
    border: BorderStroke? = null,
    content: @Composable () -> Unit
) {
    val absoluteElevation = LocalAbsoluteTonalElevation.current + tonalElevation
    CompositionLocalProvider(
        LocalContentColor provides contentColor,
        LocalAbsoluteTonalElevation provides absoluteElevation
    ) {
        Box(
            modifier = modifier
                .surface(
                    shape = shape,
                    backgroundColor = surfaceColorAtElevation(
                        color = color,
                        elevation = absoluteElevation
                    ),
                    border = border,
                    shadowElevation = shadowElevation
                )
                .semantics(mergeDescendants = false) {
                    isContainer = true
                }
                .pointerInput(Unit) {},
            propagateMinConstraints = true
        ) {
            content()
        }
    }
}
```
결국 모든 코드는 foundation, layout 라이브러리를 활용하고 있음을 알 수 있다.

Scaffold를 잘 활용해 개발하면 개발 속도도 빨라지고 좋다. 어차피 Compose는 함수를 잘 분리하고, 이들을 잘 조합해 활용하면 아주 편한 개발이 가능하다는 점이다.

