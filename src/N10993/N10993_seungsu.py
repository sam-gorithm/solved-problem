# 첫째 줄에 N(1 ≤ N ≤ 10)이 주어진다.
# 첫째 줄부터 차례대로 별을 출력한다.


def draw_stars(r, c, n):
    # 종료 조건: N = 1일 때는 별 하나만 찍고 종료
    if n == 1:
        canvas[r][c] = '*'
        return
    
    h = 2 ** n - 1    # 현재 그릴 삼각형의 세로 길이
    w = 2 * h - 1     # 현재 그릴 삼각형의 가로 길이
    
    # N이 홀수일 때: 위로 뾰족한 정삼각형
    if n % 2 == 1:
        # 1. 밑변 채우기
        for i in range(w):
            canvas[r + h - 1][c + i] = '*'
            
        # 2. 양 옆 빗변 채우기 (밑변과 겹치는 꼭짓점 제외)
        for i in range(h - 1):
            canvas[r + i][c + h - 1 - i] = '*'
            canvas[r + i][c + h - 1 + i] = '*'
            
        # 3. 내부에 들어갈 짝수(역삼각형) 재귀 호출
        # 다음 시작 좌표(r, c)를 내부 역삼각형 위치에 맞게 계산
        draw_stars(r + 2 ** (n - 1) - 1, c + 2 ** (n - 1), n - 1)
        

    # N이 짝수일 때: 아래로 뾰족한 역삼각형
    else:
        # 1. 윗변 채우기
        for i in range(w):
            canvas[r][c + i] = '*'
            
        # 2. 양 옆 빗변 채우기 (윗변과 겹치는 꼭짓점 제외)
        for i in range(1, h):
            canvas[r + i][c + i] = '*'
            canvas[r + i][c + w - 1 - i] = '*'
            
        # 3. 내부에 들어갈 홀수(정삼각형) 재귀 호출
        # 다음 시작 좌표(r, c)를 내부 정삼각형 위치에 맞게 계산
        draw_stars(r + 1, c + 2 ** (n - 1), n - 1)


N = int(input())

# 세로와 가로 최대 크기 계산
row = 2 ** N - 1
col = 2 * row - 1

# 일일이 규칙을 찾는 건 너무 복잡하므로, 재귀적으로 그리는 방법을 선택
# 가장 큰 삼각형이 들어갈 수 있는 캔버스를 공백으로 초기화
canvas = [[' '] * col for _ in range(row)]

# 배열의 가장 왼쪽 위(0, 0)를 기준으로 재귀 시작
draw_stars(0, 0, N)

# 문자열 오른쪽의 불필요한 공백 제거
for i in range(row):
    print("".join(canvas[i]).rstrip())
