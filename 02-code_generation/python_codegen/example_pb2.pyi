from google.protobuf import timestamp_pb2 as _timestamp_pb2
from google.protobuf import descriptor as _descriptor
from google.protobuf import message as _message
from typing import ClassVar as _ClassVar, Optional as _Optional

DESCRIPTOR: _descriptor.FileDescriptor

class EchoMessage(_message.Message):
    __slots__ = ("message",)
    MESSAGE_FIELD_NUMBER: _ClassVar[int]
    message: str
    def __init__(self, message: _Optional[str] = ...) -> None: ...

class RandomRequest(_message.Message):
    __slots__ = ("numbers", "min", "max")
    NUMBERS_FIELD_NUMBER: _ClassVar[int]
    MIN_FIELD_NUMBER: _ClassVar[int]
    MAX_FIELD_NUMBER: _ClassVar[int]
    numbers: int
    min: int
    max: int
    def __init__(self, numbers: _Optional[int] = ..., min: _Optional[int] = ..., max: _Optional[int] = ...) -> None: ...

class RandomResponse(_message.Message):
    __slots__ = ("value",)
    VALUE_FIELD_NUMBER: _ClassVar[int]
    value: float
    def __init__(self, value: _Optional[float] = ...) -> None: ...
