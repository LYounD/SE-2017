#!/usr/bin/python3

import sys
import pygame
import blocks
import game
import time
import math
import webbrowser
url = 'http://kr.battle.net/heroes/ko/'

tetris = game.Game()
fire_1 = pygame.image.load('fire1.png')
fire_2 = pygame.image.load('fire2.png')
fire_3 = pygame.image.load('fire3.png')
fire_4 = pygame.image.load('fire4.png')
fire_5 = pygame.image.load('fire5.png')
fire_6 = pygame.image.load('fire6.png')
storm_ = pygame.image.load('storm.png')

pygame.init()
pygame.mixer.music.load('bgm.wav')
clearsound = pygame.mixer.Sound('clear.wav')
pygame.mixer.music.play(-1,0.0)

# 블럭 색깔
colors = {1: (255, 0, 0), 2: (0, 255, 0), 3: (0, 0, 255), 4: (255, 255, 0),
    5: (0, 255, 255), 6: (255, 0, 255), 7: (255,255,255)}
def main():
    screen = pygame.display.set_mode((800, 500)) #게임 창

    pygame.display.set_caption('Tetris') #창 제목

    font = pygame.font.Font(None,60) #텍스트
    font2 = pygame.font.Font(None,40)
    text_stage = font.render('Stage',True,(255,255,255))
    text_score = font.render('Score', True, (255, 255, 255))
    text_stage_ = font2.render(str(tetris.stage),True,(200,0,0))
    text_score_ = font2.render(str(tetris.score),True,(100,100,100))
    text_nextblock = font2.render('Next Block',True,(0,150,0))
    textrect_stage = text_stage.get_rect()
    textrect_stage.center = (670,280)
    textrect_score = text_score.get_rect()
    textrect_score.center = (670, 380)
    textrect_stage_ = text_stage_.get_rect()
    textrect_stage_.center = (670, 330)
    textrect_score_ = text_score_.get_rect()
    textrect_score_.center = (670, 430)
    textrect_nextblock = text_nextblock.get_rect()
    textrect_nextblock.center = (670, 160)

    rect = pygame.Surface((25, 25)).convert() #네모 한 칸

    gameMap = initGameMap()

    lastTick = pygame.time.get_ticks()

    while tetris.game: ##메인 루프 / 화면 표시
        newTick = pygame.time.get_ticks()
        diff = newTick - lastTick
        lastTick = newTick
        update(diff)

        screen.fill((0, 0, 0))

        drawBlock(screen, rect, tetris.getGameMap(), (0, 0))
        drawBlock(screen, rect, tetris.getBlock().getRotatedShape(),tetris.getPosition())
        drawBlock(screen, rect, tetris.getnextBlock().getnextblock(), (25,1))
        pygame.draw.rect(screen, (50, 50, 50), (500, 0, 50, 500))
        screen.blit(text_nextblock, textrect_nextblock)
        screen.blit(text_stage,textrect_stage)
        screen.blit(text_score, textrect_score)
        text_stage_ = font2.render(str(tetris.stage), True, (200, 0, 0))
        screen.blit(text_stage_, textrect_stage_)
        text_score_ = font2.render(str(tetris.score), True, (100, 100, 100))
        screen.blit(text_score_, textrect_score_)
        for x in range(len(tetris.fire_state)):
            makefire = drawfire(screen,x)
            next(makefire)
            if(tetris.fire_state[x]==1):
                makefire.send(fire_1)
            elif(tetris.fire_state[x]==2):
                makefire.send(fire_2)
            elif (tetris.fire_state[x] == 3):
                makefire.send(fire_3)
            elif (tetris.fire_state[x] == 4):
                makefire.send(fire_4)
            elif (tetris.fire_state[x] == 5):
                makefire.send(fire_5)
            elif (tetris.fire_state[x] == 6):
                makefire.send(fire_6)
        pygame.display.flip()
        pygame.time.wait(10)
    pygame.display.set_caption('★☆★☆★☆☞히어로즈 오브 더 스톰☜☆★☆★☆★ 무료가입 ☞☞http://kr.battle.net/heroes/ko/☜☜')
    for x in range(0,480):
        screen.fill((0,0,100))
        storm = pygame.transform.rotate(storm_,x*15)
        screen.blit(storm,(400-storm.get_rect().width/2,250-storm.get_rect().height/2))
        pygame.display.flip()
    webbrowser.open(url)

def update(time):
    for event in pygame.event.get():
        if event.type == pygame.QUIT: #종료
            sys.exit()
        elif event.type == pygame.KEYDOWN: #키보드 입력 체크
            if event.key == pygame.K_LEFT:
                tetris.moveLeft()
            elif event.key == pygame.K_RIGHT:
                tetris.moveRight()
            elif event.key == pygame.K_UP:
                tetris.rotate()
            elif event.key == pygame.K_DOWN:
                tetris.speed = 3
        elif event.type == pygame.KEYUP:
            if event.key == pygame.K_DOWN:
                tetris.speed = 1
    tetris.update(time*(tetris.speed))

def initGameMap():
    gameMap = []
    for y in range(20):
        gameMap.append([])
        for x in range(20):
            gameMap[y].append(0)
    return gameMap

def drawBlock(screen, rect, shape, position): #블록 그리기
    for y in range(len(shape)):
        for x in range(len(shape[0])):
            if not shape[y][x] == 0:
                rect.fill(colors[shape[y][x]])
                screen.blit(rect, (25 * (x + position[0]), 25 * (y + position[1])))

def drawfire(screen,line):
    while(True):
        state = yield
        screen.blit(state,(0,line*25))
        tetris.fire_state[line] += 1
        if(tetris.fire_state[line] == 7):
            tetris.fire_state[line] = 0
        pygame.mixer.Sound.play(clearsound)
        time.sleep(0.02)

if __name__ == '__main__':
    main()
