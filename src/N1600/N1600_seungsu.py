# 말은 격자판에서 체스의 나이트와 같은 이동방식을 가진다.
# 격자판의 맨 왼쪽 위에서 시작해서 맨 오른쪽 아래까지 가야한다.
# 인접한 네 방향으로 한 번 움직이는 것, 말의 움직임으로 한 번 움직이는 것, 모두 한 번의 동작으로 친다.
# 격자판이 주어졌을 때, 원숭이가 최소한의 동작으로 시작지점에서 도착지점까지 갈 수 있는 방법?

# 첫째 줄에 정수 K(0 <= K <= 30)가 주어진다. 둘째 줄에 격자판의 가로길이 W, 세로길이 H(1 <= W, H <= 200)가 주어진다.
# 그 다음 H줄에 걸쳐 W개의 숫자가 주어지는데, 0은 아무것도 없는 평지, 1은 장애물을 뜻한다.장애물이 있는 곳으로는 이동할 수 없다.


# 첫째 줄에 원숭이의 동작수의 최솟값을 출력한다. 시작점에서 도착점까지 갈 수 없는 경우엔 -1을 출력한다.


from collections import deque


# 가능한 모든 이동 방향 정의
dx = [1, -1, 0, 0, 2, 2, -2, -2, 1, 1, -1, -1]
dy = [0, 0, 1, -1, 1, -1, 1, -1, 2, -2, 2, -2]

# bfs 수행
def horse_monkey():
    # 3차원 리스트 생성(말의 움직임 횟수에 따른 방문처리)
    visited = [[[0] * (K + 1) for _ in range(W)] for _ in range(H)]
    queue = deque()
    queue.append((0, 0, 0))  # (x, y, 말의 움직임과 같이 움직인 횟수)

    while queue:
        x, y, horse_moves = queue.popleft()

        if x == W - 1 and y == H - 1:
            # 현재까지 이동한 횟수 반환
            return visited[y][x][horse_moves]
        
        # 가능한 12가지 방향으로 이동 시도
        for i in range(12):
            nx = x + dx[i]
            ny = y + dy[i]

            # 범위 내에 있고, 장애물이 없는 경우
            if 0 <= nx < W and 0 <= ny < H and board[ny][nx] == 0:
                if i < 4:  # 인접한 네 방향
                    # 아직 방문하지 않은 경우
                    if visited[ny][nx][horse_moves] == 0:
                        # 현재까지 이동한 횟수 + 1
                        visited[ny][nx][horse_moves] = visited[y][x][horse_moves] + 1
                        queue.append((nx, ny, horse_moves))

                else:  # 말의 움직임과 같이 이동
                    # 말 움직임을 사용할 수 있는 경우
                    if horse_moves < K and visited[ny][nx][horse_moves + 1] == 0:
                        # 현재까지 이동한 횟수 + 1
                        visited[ny][nx][horse_moves + 1] = visited[y][x][horse_moves] + 1
                        queue.append((nx, ny, horse_moves + 1))

    return -1


K = int(input())
W, H = map(int, input().split())
board = [list(map(int, input().split())) for _ in range(H)]

print(horse_monkey())
