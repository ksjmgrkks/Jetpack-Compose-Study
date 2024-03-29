링크: https://developer.android.com/jetpack/compose/why-adopt?hl=ko  
</br>
## Compose를 채택하는 이유
Jetpack Compose는 네이티브 UI를 빌드하기 위한 Android의 최신 툴킷입니다.  
더 적은 수의 코드, 강력한 도구, 직관적인 Kotlin API로 Android에서의 UI 개발을 간소화하고 가속화하여 앱에 생동감을 더해줍니다.  
Android UI를 더 빠르고 쉽게 빌드할 수 있습니다. Compose를 만드는 동안 모든 이점을 직접 경험하고 요점을 공유해 준 다양한 파트너와 협력했습니다.  

## 코드 감소
코드를 적게 작성하면 개발의 모든 단계에 영향을 미칩니다.  
작성자는 테스트와 디버그 작업과 버그 발생 가능성이 줄어들어 당면 문제에 집중할 수 있게 되며  
검토자 또는 유지관리자는 읽고, 이해하고, 검토하고, 유지관리할 코드가 적어집니다.  

Compose를 사용하면 Android View 시스템을 사용할 때에 비해 더 적은 코드로 더 많은 작업을 할 수 있습니다.  
버튼, 목록 또는 애니메이션이 있으므로 이제 무엇을 빌드해야 하든 코드는 조금만 작성해도 됩니다. 다음은 Google 파트너의 의견입니다.  

- "동일한 버튼 클래스의 경우 [코드] 의 규모가 10배 더 작았습니다.” (Twitter)
- “RecyclerView로 빌드한 모든 화면에서 상당한 감소 효과가 나타났으며, 대부분의 화면이 여기에 해당합니다.” (Monzo)
- “앱에서 목록이나 애니메이션을 만드는 데 필요한 코드 줄이 이렇게 적다는 사실이 매우 기뻤습니다. 기능마다 작성하는 코드 줄 수가 줄어든 덕분에 고객에게 가치를 제공하는 데 더욱 집중할 수 있게 되었습니다.” (Cuvva)
- 작성하는 코드를 Kotlin과 XML로 나누지 않고 Kotlin으로만 작성합니다. “Kotlin과 XML 사이를 전환하는 것이 아니라 모든 것이 같은 언어로 종종 같은 파일에서 작성되면 코드를 추적하기가 훨씬 쉬워집니다.” (Monzo)
- Compose로 코드를 작성하면 빌드 중인 대상을 쉽고 간단하게 유지관리할 수 있습니다. “Compose의 레이아웃 시스템은 개념적으로 더 단순하기 때문에 추론하기도 쉽습니다. 복잡한 구성요소의 코드도 쉽게 읽을 수 있습니다.” (Square)

## 직관적
Compose는 선언적 API를 사용합니다. 즉, Compose가 나머지를 처리하므로 UI를 설명하기만 하면 됩니다. API는 직관적이므로 찾아서 사용하기가 쉽습니다. "테마 레이어가 훨씬 더 직관적이고 읽기 쉽습니다. 멀티 레이어 테마 오버레이를 통해 속성을 정의하고 할당하는 여러 XML 파일로 확장해야 했을 일을 단일한 Kotlin 파일 내에서 달성할 수 있었습니다.” (Twitter)

Compose를 사용하면 특정 활동이나 프래그먼트에 종속되지 않는 작은 스테이트리스(Stateless) 구성요소를 빌드합니다. 이를 통해 재사용하고 테스트하기가 쉬워집니다. "사용 및 유지관리가 쉽고 구현/확장/맞춤설정이 직관적인 스테이트리스 UI 구성요소의 새로운 세트를 제공하겠다는 목표를 세웠습니다. Compose는 이를 위한 좋은 해답이었습니다.” (Twitter)

Compose에서는 상태가 명시적이며 컴포저블로 전달됩니다. 이렇게 하면 상태에 관한 정보 출처가 하나이므로 캡슐화되고 분리됩니다. 그런 다음 앱 상태가 변경되면 UI가 자동으로 업데이트됩니다. “추론하는 동안 기억해야 하는 사항이 줄어들고 통제를 벗어나거나 제대로 이해하지 못하는 행동도 적어집니다.” (Cuvva)

## 빠른 개발 과정
Compose는 기존의 모든 코드와 호환됩니다. Compose에서 Views를, Views에서 Compose 코드를 호출할 수 있습니다. Navigation, ViewModel, Kotlin 코루틴과 같은 대부분의 일반적인 라이브러리는 Compose와 함께 작동하므로 언제 어디서든 원하는 대로 채택할 수 있습니다. “상호 운용성에서 Compose 통합이 시작되었으며 '사용하기 쉽다'는 사실을 알았습니다. 밝은 모드와 어두운 모드 등을 신경 쓸 필요가 전혀 없었고 전반적인 사용 환경이 매우 완벽했습니다.” (Cuvva)

실시간 미리보기와 같은 기능을 포함해 전체 Android 스튜디오 지원을 사용하면 코드를 더 빠르게 반복하고 제공할 수 있습니다. "Android 스튜디오의 미리보기 덕분에 시간이 크게 절약되었습니다. 또한 여러 미리보기도 빌드할 수 있기 때문에 시간이 절약됩니다. 오류 상태나 여러 가지 글꼴 크기처럼 상태와 설정이 다양한 UI 구성요소를 확인해야 하는 경우가 많습니다. 이때 여러 미리보기를 만들 수 있으므로 확인하기가 쉽습니다.” (Square)

## 강력한 성능
Compose는 Android 플랫폼 API에 직접 액세스하고 머티리얼 디자인, 어두운 테마, 애니메이션 등을 기본적으로 지원하여 멋진 앱을 만들 수 있습니다. "Compose는 선언적 UI보다 더 많은 문제를 해결하기도 했으며 접근성 API, 레이아웃 등 모든 항목이 개선되었습니다. 만들고 싶은 것과 실제로 만드는 것 사이의 차이가 줄어듭니다.”(Square)

Compose를 사용하면 애니메이션을 통해 쉽고 빠르게 앱에 움직임과 생명을 불어넣을 수 있습니다. “애니메이션을 Compose에 쉽게 추가할 수 있으니 색상/크기/고도 변경 등을 애니메이션으로 처리하지 않을 이유가 없습니다.”(Monzo), ““특별한 도구가 없어도 애니메이션을 만들 수 있습니다. 정적 화면을 표시하는 것과 다르지 않습니다”(Square).

머티리얼 디자인으로 빌드하든 자체 디자인 시스템으로 빌드하든, Compose를 사용하면 원하는 디자인을 유연하게 구현할 수 있습니다. "머티리얼 디자인을 기반에서 분리하면 정말 유용합니다. 자체 디자인 시스템을 구축하다 보면 머티리얼과 디자인 요구사항이 다른 경우가 많기 때문입니다.” (Square)
