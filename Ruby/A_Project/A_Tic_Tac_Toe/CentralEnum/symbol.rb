# Ruby equivalent of Symbol.java enum
# Ruby doesn't have native enums, so we use a module with frozen constants

module CentralEnum
  module Symbol
    X     = :X
    O     = :O
    EMPTY = :EMPTY
  end
end
