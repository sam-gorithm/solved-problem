# # 원판의 회전 방법
# # 번호가 x의 배수인 원판을 d 방향으로 k칸 회전시킨다. d가 0이면 시계 방향, 1이면 반시계 방향
# # 원판에 수가 남았다면, '인접하면서' '수가 같은 것'을 모두 찾는다.
#     # 1. 그러한 수가 있다면 원판에서 인접하면서 같은 수를 모두 지운다.
#     # 2. 없는 경우 원판에 적힌 수의 평균을 구하고, 평균보다 큰 수에서 1을 빼고, 작은 수에는 1을 더한다.
#
# # 원판을 T(1 <= T <= 50) 번 회전시킨 후 원판에 적힌 수의 합 구하기
from collections import deque


def rotate_circle(circle, direction, number):
    # rotate 에서 인자가 +: 시계, -: 반시계 방향 회전
    if direction == 0:
        circle.rotate(number)
    else:
        circle.rotate(-number)


def remove_adjacent(circles, n_circles, n_integers):
    removed = False     # 제거된 숫자가 있는지 체크
    to_remove = set()   # 제거해야 할 숫자들을 set으로 관리

    # 모든 원판 위 모든 숫자들에 대해서 인접 숫자 확인
    for i in range(n_circles):
        for j in range(n_integers):
            if circles[i][j] == 0: continue     # 해당 숫자가 0이면 확인 패스
            current = circles[i][j]

            # 같은 원판 내 인접 (좌/우)
            left = (j - 1) % n_integers
            right = (j + 1) % n_integers

            if circles[i][left] == current:     # 왼쪽에 인접한 숫자와 값이 같다면
                to_remove.add((i, j))   # 제거할 숫자에 삽입
                to_remove.add((i, left))
                removed = True
            if circles[i][right] == current:    # 오른쪽에 인접한 숫자와 값이 같다면
                to_remove.add((i, j))
                to_remove.add((i, right))
                removed = True

            # 위/아래 원판 같은 j 인덱스
            if i > 0 and circles[i - 1][j] == current:  # 아래쪽에 인접한 숫자와 값이 같다면
                to_remove.add((i, j))
                to_remove.add((i - 1, j))
                removed = True
            if i < n_circles - 1 and circles[i + 1][j] == current:  # 위쪽에 인접한 숫자와 값이 같다면
                to_remove.add((i, j))
                to_remove.add((i + 1, j))
                removed = True

    # 인접한 같은 숫자들 제거
    for i, j in to_remove:
        circles[i][j] = 0

    return removed


number_of_circles, number_of_integers, number_of_rotates = map(int, input().split())  # 원판의 수, 정수의 수, 회전 수
circles = list(deque(map(int, input().split())) for _ in range(number_of_circles))   # 원판에 있는 정수들의 정보

# 회전 정보([0]: 회전시킬 원판(배수 원판마다), [1]: 회전시킬 방향, [2]: 회전할 칸 수)
rotate_info = [list(map(int, input().split())) for _ in range(number_of_rotates)]

for idx in range(len(rotate_info)):     # 원판 회전
    target_rotate = rotate_info[idx][0]     # 회전 시킬 원판
    rotate_direction = rotate_info[idx][1]  # 회전 방향
    rotate_number = rotate_info[idx][2]     # 회전 횟수

    for i in range(target_rotate - 1, number_of_circles, target_rotate):   # circle index가 1부터 시작, 배수만큼 건너뛰기
        rotate_circle(circles[i], rotate_direction, rotate_number)  # 회전 시키기

    # 인접한 숫자 제거(제거한 숫자 없으면 False 반환)
    is_removed = remove_adjacent(circles, number_of_circles, number_of_integers)

    if not is_removed:    # 제거한 숫자가 없다면
        total_sum = sum(sum(circle) for circle in circles)
        non_zero_count = sum(num != 0 for circle in circles for num in circle)  # 0이 아닌 숫자들의 개수

        if non_zero_count > 0:  # 0이 아닌 숫자가 있다면 평균 계산해서 업데이트
            avg_val = total_sum / non_zero_count
            for i in range(number_of_circles):  # 모든 원판에 대해서
                for j in range(number_of_integers): # 원판 내 숫자들에 대해서
                    if circles[i][j] != 0:
                        if circles[i][j] > avg_val: # 평균보다 크면
                            circles[i][j] -= 1      # 1 빼기
                        elif circles[i][j] < avg_val:   # 평균보다 작으면
                            circles[i][j] += 1          # 1 더하기

circle_sum = sum(sum(circle) for circle in circles)
print(circle_sum)