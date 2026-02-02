# 첫째 줄에 수의 개수 N과 턴의 개수 Q가 주어진다.(1 ≤ N, Q ≤ 100,000)
# 둘째 줄에는 처음 배열에 들어가 있는 정수 N개가 주어진다.
# 세 번째 줄에서 Q+2번째 줄까지는 x y a b의 형식으로,
# "x~y까지의 합을 구하여라", "a번째 수를 b로 바꾸어라" 라는 뜻의 데이터가 주어진다

# 한 턴마다 구한 합을 한 줄마다 한 개씩 출력


# x~y는 당연히 x번째 부터 y번째가 맞다.
# 하지만, 이 문제에서는 x > y인 경우 y번째 부터 x번째이다.


# N, Q = map(int, input().split())
# numbers = list(map(int, input().split()))

# # 누적 합 배열 생성
# prefix_sum = [0] * (N + 1)

# # 누적 합 계산
# for i in range(1, N + 1):
#     prefix_sum[i] = prefix_sum[i - 1] + numbers[i - 1]

# # Q개의 턴 처리
# for _ in range(Q):
#     x, y, a, b = map(int, input().split())

#     # x~y까지의 합 구하기
#     if x > y: # x와 y의 위치 바꾸기
#         x, y = y, x

#     # 합 출력
#     print(prefix_sum[y] - prefix_sum[x - 1])

#     # a번째 수를 b로 바꾸기
#     diff = b - numbers[a - 1]
#     numbers[a - 1] = b

#     # 누적 합 배열 업데이트
#     for i in range(a, N + 1):
#         prefix_sum[i] += diff




# 구간 합으로 풀 경우 시간 초과 발생
# 구간 합은 세그먼트 트리로 풀어야 효율적임

# 세그먼트 트리 빌드 함수
def build():
    # 리프 노드에 값 설정
    for i in range(N):
        segment_tree[size + i] = numbers[i]
    
    # 부모 노드 계산
    for i in range(size - 1, 0, -1):
        segment_tree[i] = segment_tree[2 * i] + segment_tree[2 * i + 1]

# 구간 합 쿼리 함수
def range_sum(left, right):

    left += size - 1
    right += size - 1
    total = 0

    while left <= right:
        if left % 2 == 1:
            total += segment_tree[left]
            left += 1
        if right % 2 == 0:
            total += segment_tree[right]
            right -= 1
        left //= 2
        right //= 2

    return total

# 값 업데이트 함수
def update(index, value):
    # 리프 노드 업데이트
    index += size - 1
    segment_tree[index] = value

    # 부모 노드 업데이트
    while index > 1:
        index //= 2
        segment_tree[index] = segment_tree[2 * index] + segment_tree[2 * index + 1]


N, Q = map(int, input().split())
numbers = list(map(int, input().split()))

# 세그먼트 트리 초기화
size = 1
while size < N:
    size *= 2
segment_tree = [0] * (2 * size)

# 세그먼트 트리 빌드
build()

# Q개의 턴 처리
for _ in range(Q):
    x, y, a, b = map(int, input().split())

    # x ~ y까지의 합 구하기
    if x > y:
        x, y = y, x
    print(range_sum(x, y))

    # a번째 수를 b로 바꾸기
    update(a, b)
