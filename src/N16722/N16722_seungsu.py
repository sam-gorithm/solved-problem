# 도형의 모양으로는 {동그라미, 세모, 네모}, 도형의 색으로는 {노란색, 빨간색, 파란색}, 배경색으로는 {회색, 흰색, 검은색}이 존재.
# 아홉 장의 그림들의 세 가지 속성이 주어지면 게임의 플레이어는 ‘합’이 되는 세 장의 그림을 찾아야 한다.
# ‘합’이란 그림의 세 가지 속성이 모두 같거나 모두 다른 세 장의 그림 조합.

# ‘결! 합!’ 게임은 초기점수로 0점을 가지고 시작하며 플레이어가 점수를 얻을 수 있는 행동은 다음 두 가지가 있다.

    # ‘합’ 외치기: ‘합’이라고 생각되는 서로 다른 그림 세 장의 번호를 외친다.
    #             외친 번호의 그림 세 장이 ‘합’을 이루면서 이전에 외친 적이 없는 그림 조합이라면 +1점을, 아니라면 -1점을 획득한다.
    # ‘결’ 외치기: 아홉 장의 그림으로 조합 가능한 '합'들 중 외치지 않은 ‘합’이 더 이상 없다고 생각될 경우 ‘결’을 외친다.
    #             실제로 외치지 않은 ‘합’ 이 없고, ‘결’을 통해 +3점을 얻은 적이 없다면 +3점을, 아니라면 -1점을 획득한다.

# 게임에 사용될 아홉 장의 그림의 속성들과 플레이어의 게임 기록이 주어졌을 때, 플레이어의 최종 점수를 구하라.


# 첫 번째 줄부터 아홉 줄에 걸쳐 i(1 ≤ i ≤ 9)번 그림의 도형의 모양, 도형의 색, 배경색을 나타나는 Si, Ci, Bi.
# Si는 {“CIRCLE”, “TRIANGLE”, ”SQUARE”}, Ci는 {“YELLOW”, “RED”, “BLUE”}, Bi는 {“GRAY”, “WHITE”, “BLACK”} 중 하나.

# 열 번째 줄에 플레이어의 게임 기록의 수 n(1 ≤ n ≤ 100)이 주어지고,
# 다음 줄부터 n줄에 걸쳐 다음 두 가지 입력 중 하나가 주어진다.

    # H a b c : 플레이어가 ‘합’이라고 생각되는 서로 다른 그림의 번호 a, b, c를 외친 입력이다. (1 ≤ a, b, c ≤ 9)
    # G : 플레이어가 ‘결’을 외친 입력이다.


from itertools import combinations

# '합'인지 확인하는 함수
# 세 장의 카드 각 속성(도형, 색, 배경)이 모두 같거나 모두 다른지 확인
def is_hab(card1, card2, card3):
    # 각 속성 위치(0: 도형, 1: 색, 2: 배경)에 대해 확인
    for i in range(3):
        # i번째 속성의 세 값을 집합으로 만들기
        attrs = {card1[i], card2[i], card3[i]}
        # 집합 크기가 2라는 것은 두 개는 같고 하나는 다르다는 뜻 -> 유효하지 않음
        if len(attrs) == 2:
            return False
    # 모든 속성이 모두 같거나 모두 다르면 유효한 '합'
    return True

# 점수 계산 함수
def calculate_score(cards, actions):
    # 0~8 인덱스로 가능한 모든 3장 조합 생성
    card_combinations = list(combinations(range(9), 3))
    # 유효한 '합' 조합들을 집합으로 저장 (중복 확인용)
    valid_sets = {combo for combo in card_combinations if is_hab(cards[combo[0]], cards[combo[1]], cards[combo[2]])}
    
    score = 0
    called_sets = set()  # 플레이어가 외친 '합' 조합들 (중복 확인용)
    gained_gyeol_point = False  # '결'을 통해 +3점을 얻은 적이 있는지 여부

    for action in actions:
        if action[0] == 'H':  # '합' 외치기
            a, b, c = sorted(action[1:])  # 카드 번호를 정렬
            current_set = (a, b, c)
            # 유효한 '합'이고 이전에 외친 적이 없으면 +1점
            if current_set in valid_sets and current_set not in called_sets:
                score += 1
                called_sets.add(current_set)
            else:  # 유효하지 않거나 중복이면 -1점
                score -= 1

        elif action[0] == 'G':  # '결' 외치기
            # 조건 1: 모든 유효한 '합'을 외쳤는지
            # 조건 2: 아직 '결'로 +3점을 얻은 적이 없는지
            if len(called_sets) == len(valid_sets) and not gained_gyeol_point:
                # 두 조건을 모두 만족하면 +3점 획득
                score += 3
                gained_gyeol_point = True
            else:  # 조건을 만족하지 않으면 -1점
                score -= 1

    return score


# 입력 처리
# 9장의 카드 정보 입력 (도형, 색, 배경)
cards = []
for _ in range(9):
    shape, color, background = input().split()
    cards.append((shape, color, background))

# 게임 기록 수 입력
n = int(input())
actions = []

# 게임 기록 파싱
for _ in range(n):
    action = input().split()
    if action[0] == 'H':  # '합' 외치기
        # 카드 번호를 1-based에서 0-based로 변환
        actions.append((action[0], int(action[1]) - 1, int(action[2]) - 1, int(action[3]) - 1))
    else:  # '결' 외치기
        actions.append((action[0],))

# 최종 점수 계산 및 출력
final_score = calculate_score(cards, actions)
print(final_score)
