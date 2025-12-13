# RPG MAKEFILE - WINDOWS VERSION
JC = javac
JAVA = java
RM = rmdir /s /q
MKDIR = mkdir
BIN = bin

.PHONY: all clean test1 test2 help

all: compile

# Método SIMPLES - compila tudo de uma vez na ordem correta
compile:
	@echo Compilando projeto...
	@if not exist $(BIN) $(MKDIR) $(BIN)
	@$(JC) -d $(BIN) rpg/personagens/raca/Raca.java
	@$(JC) -d $(BIN) -cp $(BIN) rpg/personagens/Personagem.java
	@$(JC) -d $(BIN) -cp $(BIN) rpg/personagens/classes/*.java
	@$(JC) -d $(BIN) -cp $(BIN) rpg/personagens/status/*.java
	@$(JC) -d $(BIN) -cp $(BIN) rpg/inimigos/*.java
	@$(JC) -d $(BIN) -cp $(BIN) rpg/combate/*.java
	@$(JC) -d $(BIN) -cp $(BIN) rpg/testes/*.java
	@echo Compilacao concluida!

test1: compile
	@echo.
	@echo ==============================
	@echo TESTE: Classe Especifica
	@echo ==============================
	@$(JAVA) -cp $(BIN) rpg.testes.TesteClasseEspecifica

test2: compile
	@echo.
	@echo ==============================
	@echo TESTE: 1v1 Rei Demonio
	@echo ==============================
	@$(JAVA) -cp $(BIN) rpg.testes.TesteReiDemonio1v1

clean:
	@if exist $(BIN) $(RM) $(BIN)
	@echo Arquivos limpos!

help:
	@echo.
	@echo COMANDOS:
	@echo   make        Compilar tudo
	@echo   make test1  Testar classe especifica
	@echo   make test2  1v1 contra Rei Demonio
	@echo   make clean  Limpar arquivos
	@echo.