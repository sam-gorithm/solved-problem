# 레벨-L 버거는 다음과 같이 만든다.
    # 레벨-0 버거는 패티만으로 이루어져 있다.
    # 레벨-L 버거는 햄버거번, 레벨-(L-1) 버거, 패티, 레벨-(L-1)버거, 햄버거번으로 이루어져 있다. (L ≥ 1)

# 햄버거의 아래 X장을 먹었을 때, 먹은 패티는 몇 장일까? 한 장은 햄버거번 또는 패티 한 장이다.

# 1 ≤ N ≤ 50
# 1 ≤ X ≤ 레벨-N 버거에 있는 레이어의 수


# X번째 레이어부터 먹을 때의 패티 개수 계산 (재귀)
def count_patties_eaten(level, x):
    # 레벨-0은 패티 1장
    if level == 0:
        return 1
    
    if x == 1:
        # 첫 번만 먹음 (패티 없음)
        return 0
    elif x <= 1 + length[level - 1]:
        # 첫 번 + 첫 레벨-(level-1)의 일부
        return count_patties_eaten(level - 1, x - 1)
    elif x == 1 + length[level - 1] + 1:
        # 첫 번 + 모든 첫 레벨-(level-1) + 가운데 패티
        return patties[level - 1] + 1
    elif x <= 1 + length[level - 1] + 1 + length[level - 1]:
        # 첫 번 + 모든 첫 레벨-(level-1) + 가운데 패티 + 두 번째 레벨-(level-1)의 일부
        return patties[level - 1] + 1 + count_patties_eaten(level - 1, x - 1 - length[level - 1] - 1)
    else:
        # 전체 레벨-level 버거 모두
        return patties[level - 1] + patties[level - 1] + 1


N, X = map(int, input().split())

# 각 레벨의 총 길이와 패티 개수 미리 계산
# 레벨-0: 패티 1 장
# 레벨-1: 번 + 레벨-0 + 패티 + 레벨-0 + 번 = 5장 (패티 3)
length = [1] * (N + 1)
patties = [1] * (N + 1)

# 레벨-L: 2*length[L-1] + 3장, 2*patties[L-1] + 1 개
for i in range(1, N + 1):
    length[i] = 2 * length[i - 1] + 3
    patties[i] = 2 * patties[i - 1] + 1

print(count_patties_eaten(N, X))
