# N×N크기의 땅이 있고, 땅은 1×1개의 칸으로 나누어져 있다.
# 각각의 땅에는 나라가 하나씩 존재하며, r행 c열에 있는 나라에는 A[r][c]명이 살고 있다
# 인접한 나라 사이에는 국경선이 존재한다.

# 인구 이동은 하루 동안 다음과 같이 진행되고, 더 이상 아래 방법에 의해 인구 이동이 없을 때까지 지속된다.
    # 국경선을 공유하는 두 나라의 인구 차이가 L명 이상, R명 이하라면, 두 나라가 공유하는 국경선을 오늘 하루 동안 연다.
    # 위의 조건에 의해 열어야하는 국경선이 모두 열렸다면, 인구 이동을 시작한다.
    # 국경선이 열려있어 인접한 칸만을 이용해 이동할 수 있으면, 그 나라를 오늘 하루 동안은 연합이라고 한다.
    # 연합을 이루고 있는 각 칸의 인구수는 (연합의 인구수) / (연합을 이루고 있는 칸의 개수)가 된다. 편의상 소수점은 버린다.
    # 연합을 해체하고, 모든 국경선을 닫는다.

# N, L, R이 주어졌을 때, 인구 이동이 몇 일 동안 발생하는지 구하자.


from collections import deque

#     상 하  좌 우
dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

# bfs로 특정한 나라와 연결된 나라들을 찾기
def bfs(x, y, index):
    united = [] # 연합에 속한 나라의 좌표들
    united.append((x, y))

    queue = deque()
    queue.append((x, y))

    union[x][y] = index # 현재 연합의 번호 할당

    summary = A[x][y]   # 연합의 전체 인구 수
    count = 1   # 연합의 나라 수

    while queue:
        x, y = queue.popleft()

        for i in range(4): # 네 방향 확인
            nx = x + dx[i]
            ny = y + dy[i]

            # 범위 내에 있고, 아직 연합에 속하지 않은 나라라면
            if 0 <= nx < N and 0 <= ny < N and union[nx][ny] == -1:
                # 인구 차이가 L명 이상, R명 이하라면
                if L <= abs(A[x][y] - A[nx][ny]) <= R:
                    # 해당 나라를 연합에 추가
                    union[nx][ny] = index
                    queue.append((nx, ny))

                    # 연합의 인구 수와 나라 수 갱신
                    summary += A[nx][ny]
                    count += 1

                    # 연합에 속한 나라의 좌표 기록
                    united.append((nx, ny))

    # 연합의 인구 수를 연합을 이루고 있는 모든 나라에 분배
    for i, j in united:
        A[i][j] = summary // count

    return count

# 1 <= N <= 50, 1 <= L <= R <= 100
N, L, R = map(int, input().split())
# 0 <= A[r][c] <= 100
A = [list(map(int, input().split())) for _ in range(N)]

result = 0

# 인구 이동이 없을 때까지 반복
while True:
    # 연합 정보 초기화
    union = [[-1] * N for _ in range(N)]
    # 연합 인덱스
    index = 0

    # 모든 칸을 돌며 연합을 체크
    for i in range(N):
        for j in range(N):
            if union[i][j] == -1:   # 아직 연합이 없는 칸이라면
                bfs(i, j, index)
                # 다음 연합 인덱스 증가
                index += 1
    # 모든 칸이 각각 연합을 이루고 있다
    # -> 인구 이동이 없다
    if index == N * N:
        break

    # 인구 이동 발생
    result += 1

print(result)
