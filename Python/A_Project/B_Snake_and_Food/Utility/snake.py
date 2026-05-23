from collections import deque

from Utility.pair import Pair


class Snake:
    def __init__(self, start=None):
        self.body_segments = deque()
        if start is None:
            self.body_segments.appendleft(Pair(0, 0))
        else:
            self.body_segments.appendleft(start)

    def head(self):
        return self.body_segments[0]

    def tail(self):
        return self.body_segments[-1]

    def move_to(self, new_head):
        self.body_segments.appendleft(new_head)
        self.body_segments.pop()

    def grow_to(self, new_head):
        self.body_segments.appendleft(new_head)

    def length(self) -> int:
        return len(self.body_segments)

    def body(self):
        return self.body_segments
