# -*- coding: cp1252 -*-
import sys
from random import shuffle

def clear(s):
    k=s**2
    return k

class spil(object):
    def spilastokkur(self):
        spil =["H1", "H2", "H3", "H4", "H5", "H6", "H7", "H8", "H9", "H10", "HG", "HD", "HK", "S1", "S2", "S3", "S4", "S5", "S6", "S7", "S8", "S9", "S10", "SG", "SD", "SK", "T1", "T2", "T3", "T4", "T5", "T6", "T7", "T8", "T9", "T10", "TG", "TD", "TK", "L1", "L2", "L3", "L4", "L5", "L6", "L7", "L8", "L9", "L10", "LG", "LD", "LK"]
        shuffle(spil)
        return spil
def main():
    cards = spil()
    stokkur = cards.spilastokkur()
    hendi = []
    hendi.append(stokkur.pop())
    hendi.append(stokkur.pop())
    hendi.append(stokkur.pop())
    i=3
    print("Reglur kapalsins eru eftirfarandi")
    print("Ef spilin � endunum hafa s�mu sort, geturu losa� �ig vi� spilin tv� � milli �eirra")
    print("Eg spilin � endunum hafa s�mu t�lu e�a gildi, geturu losa� �ig vi� �ll fj�gur spilin")
    print("�� vinnur ef �� getur enda� me� f�rri en fj�gur spil")
    while(len(stokkur)>0):
        if(len(hendi)<5):
            print(hendi)
        else:
            print(hendi[i-4], hendi[i-3], hendi[i-2], hendi[i-1])
        input_var = input("settu inn 1 fyrir draga, 2 fyrir a�ger�, 3 til a� h�tta: ")
        if(input_var == 3):
            print("�� tapa�ir")
            sys.exit(0)
        elif(input_var==1):
            hendi.append(stokkur.pop())
            i=len(hendi)
        elif(input_var==2):
            if(hendi[i-1][0]==hendi[i-4][0]):
                hendi.pop(i-2)
                hendi.pop(i-3)
                i=len(hendi)
            elif(hendi[i-1][1]==hendi[i-4][1]):
                hendi.pop(i-1)
                hendi.pop(i-2)
                hendi.pop(i-3)
                hendi.pop(i-4)
                i=len(hendi)
            else:
                print("getur ekki gert neina a�ger� � �essari st��u")
        else:
            print("�� ver�ur a� velja 1, 2 e�a 3")
    if(len(hendi)<4):
        print("til hamingju �� hefur unni�")
        sys.exit(0)
    else:
        print("n� er stokkurinn b�inn, svo �egar �� dregur n�na �� ertu einungis a� setja aftasta spili� fremst, en �� m�tt halda �fram �ar til �� ert tilb�inn a� gefast upp e�a vinnur.")
        print(hendi)
        while(len(hendi)>3):
            if(len(hendi)<5):
                print(hendi)
            else:
                print(hendi[i-4], hendi[i-3], hendi[i-2], hendi[i-1])
            input_var = input("settu inn 1 fyrir draga, 2 fyrir a�ger�, 3 til a� h�tta: ")
            if(input_var == 3):
                print("�� tapa�ir")
                sys.exit(0)
            elif(input_var==1):
                hendi.append(hendi.pop(0))
            elif(input_var==2):
                if(hendi[i-1][0]==hendi[i-4][0]):
                    hendi.pop(i-2)
                    hendi.pop(i-3)
                    i=len(hendi)
                elif(hendi[i-1][1]==hendi[i-4][1]):
                    hendi.pop(i-1)
                    hendi.pop(i-2)
                    hendi.pop(i-3)
                    hendi.pop(i-4)
                    i=len(hendi)
                else:
                    print("getur ekki gert neina a�ger� � �essari st��u")
            else:
                print("�� ver�ur a� velja 1, 2 e�a 3")
        print("til hamingju �� hefur unni�")
main()
