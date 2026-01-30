# 상근이네 집에서 출발하여 맥주를 마시면서 락 페스티벌 가기
# 한 박스에 맥주 20개 있고, 50미터에 한 병씩 마시려고 함
# 박스에 맥주가 다 떨어지면 편의점에서 맥주를 사야 하고, 박스에 있는 맥주는 20병을 넘을 수 없음
# 편의점, 상근이네 집, 락 페스티벌 좌표가 주어졌을 때, 상근이네 집에서 출발하여 락 페스티벌까지 갈 수 있는지 구하기

from collections import deque

t = int(input())    # t <= 50

for _ in range(t):
    n = int(input())    # 0 <= n <= 100
    places = []         # 좌표들 저장할 리스트

    for _ in range(n + 2):  # 좌표 저장
        x, y = map(int, input().split())
        places.append((x, y))

    # BFS로 탐색
    visited = [False] * (n + 2)
    queue = deque([0])
    visited[0] = True   # 상근이네 집에서 출발
    sanggeun = "sad"    # 기본값은 sad

    while queue:
        current = queue.popleft()   # 현재 위치
        if current == n + 1:    # 락 페스티벌에 도착한 경우
            sanggeun = "happy"
            break

        # 인접한 편의점이나 락 페스티벌 탐색
        for i in range(n + 2):
            if not visited[i]:
                # 맥주로 갈 수 있는 거리인지 확인
                dist = abs(places[current][0] - places[i][0]) + abs(places[current][1] - places[i][1])
                
                # 맥주 20병 × 50m = 최대 1000m 이동 가능
                if dist <= 1000:
                    visited[i] = True
                    queue.append(i)

    print(sanggeun)
