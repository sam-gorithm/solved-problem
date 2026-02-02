# Quento 게임은 게임판은 항상 3×3 크기, 검정색 칸에는 숫자, 흰색 칸에는 +또는 -가 적혀져 있음
# 이미 방문한 칸은 재방문 할 수 없으며, 다시 지나가는 것도 불가능

# 만들어야하는 숫자 N과 사용해야 하는 숫자의 개수 M이 주어질 때,
# M개의 숫자와 연산자를 사용하여 N을 만들 수 있는지 확인

# 만들 수 있다면 첫째 줄에 1 출력, 없으면 0 출력
# 숫자를 만들 수 있는 경우, 방문한 칸을 총 2*M-1개 줄에 걸쳐서 출력


# 딱 봐도 DFS 백트래킹 문제
def quento(board, N, M):    
    directions = [(-1, 0), (1, 0), (0, -1), (0, 1)] # 상하좌우
    result_path = [] # 결과 경로 저장

    def is_valid(x, y): # 범위 체크
        return 0 <= x < 3 and 0 <= y < 3
    
    def dfs(x, y, current_value, count, path):
        """
        x, y           : 현재 위치 (숫자 칸)
        current_value  : 현재까지 계산된 값
        count          : 사용한 숫자의 개수
        path           : 방문한 칸들의 좌표 리스트
        """

        # 숫자 M개를 모두 사용한 경우
        if count == M:
            if current_value == N:  # N과 일치하는지 확인
                result_path.extend(path)
                return True
            return False

        # 다음 칸 탐색
        for dx, dy in directions:
            nx, ny = x + dx, y + dy

            if is_valid(nx, ny) and (nx, ny) not in path:   # 범위 내에 있고, 아직 방문하지 않은 칸
                if board[nx][ny] in ['+', '-']: # 연산자 칸

                    # 다음 숫자 칸 탐색
                    for ddx, ddy in directions:
                        nnx, nny = nx + ddx, ny + ddy

                        # 다음 숫자 칸이 유효하고, 방문하지 않았으며 숫자인 경우
                        if is_valid(nnx, nny) and (nnx, nny) not in path and board[nnx][nny].isdigit():
                            next_value = int(board[nnx][nny])

                            if board[nx][ny] == '+':    # 덧셈 연산
                                new_value = current_value + next_value
                            else:                       # 뺄셈 연산
                                new_value = current_value - next_value

                            # 다음 숫자 칸으로 이동
                            if dfs(nnx, nny, new_value, count + 1, path + [(nx, ny), (nnx, nny)]):
                                return True # 답을 찾은 경우
                            
        # 모든 경로 탐색 후에도 답을 찾지 못한 경우
        return False

    # 모든 숫자 칸에서 시작
    for i in range(3):
        for j in range(3):
            if board[i][j].isdigit():
                # 시작 숫자에서 DFS 시작
                if dfs(i, j, int(board[i][j]), 1, [(i, j)]):
                    print(1)    # 만들 수 있음
                    for x, y in result_path:    # 방문한 칸 출력
                        print(x, y)
                    return  # 답을 찾은 경우 종료
                
    print(0)    # 만들 수 없음


N, M = map(int, input().split())
board = [list(input().strip()) for _ in range(3)]
quento(board, N, M)
