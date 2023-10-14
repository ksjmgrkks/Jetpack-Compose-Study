# [State]
##### Jetpack Compose에서 state는 앱의 UI가 현재 어떤 상태에 있는지를 나타내는 데이터입니다.
##### 예를 들어, 체크 박스의 상태는 체크되었는지 아닌지를 나타냅니다. 또한, state는 UI에서 변경될 수 있습니다.
##### UI 상태 생성하기 : https://developer.android.com/topic/architecture/ui-layer/state-production?hl=ko#compose-state
</br>

# [Remember]
##### remember 함수는 Composable 함수에서 state를 보존하는 데 사용됩니다. Composable 함수가 recompose될 때 state가 유지되도록 합니다.
</br>

# [RememberSaveable]
##### rememberSaveable는 remember 함수와 유사하나,
##### 이 함수는 프로세스 종료 및 복구 시나리오(예: 화면 회전)에서 상태를 저장하고 복구하는 기능이 추가되었습니다.
##### 즉, 이 함수는 시스템에서 필요에 따라 프로세스를 종료해야 할 때 상태를 저장하고, 사용자가 앱으로 돌아올 때 해당 상태를 복원합니다.
</br>

## remember는 화면이 회전되면 state를 저장하지 못할까요?
##### 맞습니다. remember 함수는 컴포저블의 recomposition에서 상태를 유지하게 돕지만, 컴포넌트의 생명주기 동안만 상태를 유지합니다.
##### 그래서 컴포넌트가 제거되거나 액티비티나 프래그먼트가 재생성되는 경우(예: 화면 회전, 시스템 메모리 관리 등)에는 해당 상태를 유지하지 못합니다.
##### 이와 달리, rememberSaveable은 상태를 유지하면서도 컴포넌트의 생명주기를 넘어서 상태를 유지합니다.
##### rememberSaveable이 관리하는 상태는 컴포넌트가 제거되거나 화면이 회전하는 등의 시나리오에서도 유지됩니다.
##### 이는 rememberSaveable이 시스템에 의해 앱 프로세스가 종료되는 경우에도 상태를 유지하기 위해 별도의 메커니즘을 활용하기 때문입니다.
</br>

## recomposition은 언제 발생할까요?
##### Jetpack Compose에서 recomposition은 Composable 함수가 다시 실행되는 과정을 말합니다.
##### 즉, 어떤 값이나 상태가 변경될 때 그 변경을 반영하기 위해 해당 Composable 함수가 재실행되는 것입니다.
</br>
