# n개의 정수로 이루어진 수열이 있다. 이 수열에서 한 정수를 제거하거나, 또는 두 정수를 제거할 수 있다.
# 한 정수를 제거하는 경우에는 그 정수가 점수가 되고, 두 정수를 제거하는 경우에는 두 정수의 곱이 점수가 된다.
# 이를 반복하여 수열에 아무 수도 남지 않게 되었을 때, 점수의 총 합의 최대를 구하자.

# 첫째 줄에 정수 n(1 ≤ n ≤ 100,000)이 주어진다. 다음 n개의 줄에는 절댓값이 1,000,000을 넘지 않는 정수가 n개 주어진다.

n = int(input())
positive = []
negative = []
zero_count = 0

# 양수, 음수 따로 분리
for _ in range(n):
    num = int(input())
    if num > 0:
        positive.append(num)
    elif num < 0:
        negative.append(num)
    else:
        zero_count += 1

# 양수는 내림차순, 음수는 오름차순 정렬
positive.sort(reverse=True)
negative.sort()

result = 0

# 양수 처리
i = 0   # 인덱스
while i < len(positive):
    # 1이 아니고 짝이 있는 경우 곱하기
    if i + 1 < len(positive) and positive[i] != 1 and positive[i + 1] != 1:        
        result += positive[i] * positive[i + 1]
        i += 2  # 인덱스 둘 증가

    # 1이 포함되거나 짝이 없는 경우 더하기
    else:
        result += positive[i]
        i += 1  # 인덱스 하나 증가

# 음수 처리
i = 0
while i < len(negative):
    if i + 1 < len(negative):
        result += negative[i] * negative[i + 1]
        i += 2
    else:
        if zero_count > 0:
            # 0이 있으면 음수를 제거하는 것이 유리
            zero_count -= 1
        else:
            result += negative[i]
        i += 1

print(result)
