package G_Projects.C_Chess.PieceFactoryPackage;

import G_Projects.C_Chess.PieceFactoryPackage.ConcretePieces.Bishop;
import G_Projects.C_Chess.PieceFactoryPackage.ConcretePieces.King;
import G_Projects.C_Chess.PieceFactoryPackage.ConcretePieces.Knight;
import G_Projects.C_Chess.PieceFactoryPackage.ConcretePieces.Pawn;
import G_Projects.C_Chess.PieceFactoryPackage.ConcretePieces.Queen;
import G_Projects.C_Chess.PieceFactoryPackage.ConcretePieces.Rook;

public class PieceFactory {
    public static Piece createPiece(String pieceType, boolean isWhitePiece){
        switch(pieceType){
            case "king":
                return new King(isWhitePiece);
            case "queen":
                return new Queen(isWhitePiece);
            case "bishop":
                return new Bishop(isWhitePiece);
            case "knight":
                return new Knight(isWhitePiece);
            case "rook":
                return new Rook(isWhitePiece);
            case "pawn":
                return new Pawn(isWhitePiece);
            default:
                throw new IllegalArgumentException("Invalid piece type: " + pieceType);
        }
    }
}
