package com.janson.netty.demos.protocol;


/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/11/18 2:51 下午
 */
public final class MsgProtos {

    private MsgProtos() {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public interface MsgOrBuilder extends
            // @@protoc_insertion_point(interface_extends:com.crazymakercircle.netty.protocol.Msg)
            com.google.protobuf.MessageOrBuilder {

        /**
         * <pre>
         * Unique ID number for this person.
         * </pre>
         *
         * <code>uint32 id = 1;</code>
         */
        int getId();

        /**
         * <code>string content = 2;</code>
         */
        java.lang.String getContent();

        /**
         * <code>string content = 2;</code>
         */
        com.google.protobuf.ByteString
        getContentBytes();
    }

    /**
     * <pre>
     * [开始 消息定义]
     * </pre>
     * <p>
     * Protobuf type {@code com.crazymakercircle.netty.protocol.Msg}
     */
    public static final class Msg extends
            com.google.protobuf.GeneratedMessageV3 implements
            // @@protoc_insertion_point(message_implements:com.crazymakercircle.netty.protocol.Msg)
            MsgOrBuilder {
        private static final long serialVersionUID = 0L;

        // Use Msg.newBuilder() to construct.
        private Msg(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
            super(builder);
        }

        private Msg() {
            id_ = 0;
            content_ = "";
        }

        @java.lang.Override
        public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
            return this.unknownFields;
        }

        private Msg(
                com.google.protobuf.CodedInputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            this();
            if (extensionRegistry == null) {
                throw new java.lang.NullPointerException();
            }
            int mutable_bitField0_ = 0;
            com.google.protobuf.UnknownFieldSet.Builder unknownFields =
                    com.google.protobuf.UnknownFieldSet.newBuilder();
            try {
                boolean done = false;
                while (!done) {
                    int tag = input.readTag();
                    switch (tag) {
                        case 0:
                            done = true;
                            break;
                        case 8: {

                            id_ = input.readUInt32();
                            break;
                        }
                        case 18: {
                            java.lang.String s = input.readStringRequireUtf8();

                            content_ = s;
                            break;
                        }
                        default: {
                            if (!parseUnknownFieldProto3(
                                    input, unknownFields, extensionRegistry, tag)) {
                                done = true;
                            }
                            break;
                        }
                    }
                }
            } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage(this);
            } catch (java.io.IOException e) {
                throw new com.google.protobuf.InvalidProtocolBufferException(
                        e).setUnfinishedMessage(this);
            } finally {
                this.unknownFields = unknownFields.build();
                makeExtensionsImmutable();
            }
        }

        public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
            return internal_static_com_crazymakercircle_netty_protocol_Msg_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
            return internal_static_com_crazymakercircle_netty_protocol_Msg_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            Msg.class, Msg.Builder.class);
        }

        public static final int ID_FIELD_NUMBER = 1;
        private int id_;

        /**
         * <pre>
         * Unique ID number for this person.
         * </pre>
         *
         * <code>uint32 id = 1;</code>
         */
        public int getId() {
            return id_;
        }

        public static final int CONTENT_FIELD_NUMBER = 2;
        private volatile java.lang.Object content_;

        /**
         * <code>string content = 2;</code>
         */
        public java.lang.String getContent() {
            java.lang.Object ref = content_;
            if (ref instanceof java.lang.String) {
                return (java.lang.String) ref;
            } else {
                com.google.protobuf.ByteString bs =
                        (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                content_ = s;
                return s;
            }
        }

        /**
         * <code>string content = 2;</code>
         */
        public com.google.protobuf.ByteString
        getContentBytes() {
            java.lang.Object ref = content_;
            if (ref instanceof java.lang.String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8(
                                (java.lang.String) ref);
                content_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        private byte memoizedIsInitialized = -1;

        @java.lang.Override
        public final boolean isInitialized() {
            byte isInitialized = memoizedIsInitialized;
            if (isInitialized == 1) return true;
            if (isInitialized == 0) return false;

            memoizedIsInitialized = 1;
            return true;
        }

        @java.lang.Override
        public void writeTo(com.google.protobuf.CodedOutputStream output)
                throws java.io.IOException {
            if (id_ != 0) {
                output.writeUInt32(1, id_);
            }
            if (!getContentBytes().isEmpty()) {
                com.google.protobuf.GeneratedMessageV3.writeString(output, 2, content_);
            }
            unknownFields.writeTo(output);
        }

        @java.lang.Override
        public int getSerializedSize() {
            int size = memoizedSize;
            if (size != -1) return size;

            size = 0;
            if (id_ != 0) {
                size += com.google.protobuf.CodedOutputStream
                        .computeUInt32Size(1, id_);
            }
            if (!getContentBytes().isEmpty()) {
                size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, content_);
            }
            size += unknownFields.getSerializedSize();
            memoizedSize = size;
            return size;
        }

        @java.lang.Override
        public boolean equals(final java.lang.Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Msg)) {
                return super.equals(obj);
            }
            Msg other = (Msg) obj;

            boolean result = true;
            result = result && (getId()
                    == other.getId());
            result = result && getContent()
                    .equals(other.getContent());
            result = result && unknownFields.equals(other.unknownFields);
            return result;
        }

        @java.lang.Override
        public int hashCode() {
            if (memoizedHashCode != 0) {
                return memoizedHashCode;
            }
            int hash = 41;
            hash = (19 * hash) + getDescriptor().hashCode();
            hash = (37 * hash) + ID_FIELD_NUMBER;
            hash = (53 * hash) + getId();
            hash = (37 * hash) + CONTENT_FIELD_NUMBER;
            hash = (53 * hash) + getContent().hashCode();
            hash = (29 * hash) + unknownFields.hashCode();
            memoizedHashCode = hash;
            return hash;
        }

        public static Msg parseFrom(
                java.nio.ByteBuffer data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static Msg parseFrom(
                java.nio.ByteBuffer data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static Msg parseFrom(
                com.google.protobuf.ByteString data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static Msg parseFrom(
                com.google.protobuf.ByteString data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static Msg parseFrom(byte[] data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static Msg parseFrom(
                byte[] data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static Msg parseFrom(java.io.InputStream input)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input);
        }

        public static Msg parseFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input, extensionRegistry);
        }

        public static Msg parseDelimitedFrom(java.io.InputStream input)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseDelimitedWithIOException(PARSER, input);
        }

        public static Msg parseDelimitedFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
        }

        public static Msg parseFrom(
                com.google.protobuf.CodedInputStream input)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input);
        }

        public static Msg parseFrom(
                com.google.protobuf.CodedInputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input, extensionRegistry);
        }

        @java.lang.Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Msg prototype) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
        }

        @java.lang.Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE
                    ? new Builder() : new Builder().mergeFrom(this);
        }

        @java.lang.Override
        protected Builder newBuilderForType(
                com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        /**
         * <pre>
         * [开始 消息定义]
         * </pre>
         * <p>
         * Protobuf type {@code com.crazymakercircle.netty.protocol.Msg}
         */
        public static final class Builder extends
                com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
                // @@protoc_insertion_point(builder_implements:com.crazymakercircle.netty.protocol.Msg)
                MsgOrBuilder {
            public static final com.google.protobuf.Descriptors.Descriptor
            getDescriptor() {
                return internal_static_com_crazymakercircle_netty_protocol_Msg_descriptor;
            }

            @java.lang.Override
            protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
            internalGetFieldAccessorTable() {
                return internal_static_com_crazymakercircle_netty_protocol_Msg_fieldAccessorTable
                        .ensureFieldAccessorsInitialized(
                                Msg.class, Msg.Builder.class);
            }

            // Construct using Msg.newBuilder()
            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(
                    com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
                super(parent);
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                if (com.google.protobuf.GeneratedMessageV3
                        .alwaysUseFieldBuilders) {
                }
            }

            @java.lang.Override
            public Builder clear() {
                super.clear();
                id_ = 0;

                content_ = "";

                return this;
            }

            @java.lang.Override
            public com.google.protobuf.Descriptors.Descriptor
            getDescriptorForType() {
                return internal_static_com_crazymakercircle_netty_protocol_Msg_descriptor;
            }

            @java.lang.Override
            public Msg getDefaultInstanceForType() {
                return Msg.getDefaultInstance();
            }

            @java.lang.Override
            public Msg build() {
                Msg result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException(result);
                }
                return result;
            }

            @java.lang.Override
            public Msg buildPartial() {
                Msg result = new Msg(this);
                result.id_ = id_;
                result.content_ = content_;
                onBuilt();
                return result;
            }

            @java.lang.Override
            public Builder clone() {
                return (Builder) super.clone();
            }

            @java.lang.Override
            public Builder setField(
                    com.google.protobuf.Descriptors.FieldDescriptor field,
                    java.lang.Object value) {
                return (Builder) super.setField(field, value);
            }

            @java.lang.Override
            public Builder clearField(
                    com.google.protobuf.Descriptors.FieldDescriptor field) {
                return (Builder) super.clearField(field);
            }

            @java.lang.Override
            public Builder clearOneof(
                    com.google.protobuf.Descriptors.OneofDescriptor oneof) {
                return (Builder) super.clearOneof(oneof);
            }

            @java.lang.Override
            public Builder setRepeatedField(
                    com.google.protobuf.Descriptors.FieldDescriptor field,
                    int index, java.lang.Object value) {
                return (Builder) super.setRepeatedField(field, index, value);
            }

            @java.lang.Override
            public Builder addRepeatedField(
                    com.google.protobuf.Descriptors.FieldDescriptor field,
                    java.lang.Object value) {
                return (Builder) super.addRepeatedField(field, value);
            }

            @java.lang.Override
            public Builder mergeFrom(com.google.protobuf.Message other) {
                if (other instanceof Msg) {
                    return mergeFrom((Msg) other);
                } else {
                    super.mergeFrom(other);
                    return this;
                }
            }

            public Builder mergeFrom(Msg other) {
                if (other == Msg.getDefaultInstance()) return this;
                if (other.getId() != 0) {
                    setId(other.getId());
                }
                if (!other.getContent().isEmpty()) {
                    content_ = other.content_;
                    onChanged();
                }
                this.mergeUnknownFields(other.unknownFields);
                onChanged();
                return this;
            }

            @java.lang.Override
            public final boolean isInitialized() {
                return true;
            }

            @java.lang.Override
            public Builder mergeFrom(
                    com.google.protobuf.CodedInputStream input,
                    com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                    throws java.io.IOException {
                Msg parsedMessage = null;
                try {
                    parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                    parsedMessage = (Msg) e.getUnfinishedMessage();
                    throw e.unwrapIOException();
                } finally {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                }
                return this;
            }

            private int id_;

            /**
             * <pre>
             * Unique ID number for this person.
             * </pre>
             *
             * <code>uint32 id = 1;</code>
             */
            public int getId() {
                return id_;
            }

            /**
             * <pre>
             * Unique ID number for this person.
             * </pre>
             *
             * <code>uint32 id = 1;</code>
             */
            public Builder setId(int value) {

                id_ = value;
                onChanged();
                return this;
            }

            /**
             * <pre>
             * Unique ID number for this person.
             * </pre>
             *
             * <code>uint32 id = 1;</code>
             */
            public Builder clearId() {

                id_ = 0;
                onChanged();
                return this;
            }

            private java.lang.Object content_ = "";

            /**
             * <code>string content = 2;</code>
             */
            public java.lang.String getContent() {
                java.lang.Object ref = content_;
                if (!(ref instanceof java.lang.String)) {
                    com.google.protobuf.ByteString bs =
                            (com.google.protobuf.ByteString) ref;
                    java.lang.String s = bs.toStringUtf8();
                    content_ = s;
                    return s;
                } else {
                    return (java.lang.String) ref;
                }
            }

            /**
             * <code>string content = 2;</code>
             */
            public com.google.protobuf.ByteString
            getContentBytes() {
                java.lang.Object ref = content_;
                if (ref instanceof String) {
                    com.google.protobuf.ByteString b =
                            com.google.protobuf.ByteString.copyFromUtf8(
                                    (java.lang.String) ref);
                    content_ = b;
                    return b;
                } else {
                    return (com.google.protobuf.ByteString) ref;
                }
            }

            /**
             * <code>string content = 2;</code>
             */
            public Builder setContent(
                    java.lang.String value) {
                if (value == null) {
                    throw new NullPointerException();
                }

                content_ = value;
                onChanged();
                return this;
            }

            /**
             * <code>string content = 2;</code>
             */
            public Builder clearContent() {

                content_ = getDefaultInstance().getContent();
                onChanged();
                return this;
            }

            /**
             * <code>string content = 2;</code>
             */
            public Builder setContentBytes(
                    com.google.protobuf.ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                checkByteStringIsUtf8(value);

                content_ = value;
                onChanged();
                return this;
            }

            @java.lang.Override
            public final Builder setUnknownFields(
                    final com.google.protobuf.UnknownFieldSet unknownFields) {
                return super.setUnknownFieldsProto3(unknownFields);
            }

            @java.lang.Override
            public final Builder mergeUnknownFields(
                    final com.google.protobuf.UnknownFieldSet unknownFields) {
                return super.mergeUnknownFields(unknownFields);
            }


            // @@protoc_insertion_point(builder_scope:com.crazymakercircle.netty.protocol.Msg)
        }

        // @@protoc_insertion_point(class_scope:com.crazymakercircle.netty.protocol.Msg)
        private static final Msg DEFAULT_INSTANCE;

        static {
            DEFAULT_INSTANCE = new Msg();
        }

        public static Msg getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        private static final com.google.protobuf.Parser<Msg>
                PARSER = new com.google.protobuf.AbstractParser<Msg>() {
            @java.lang.Override
            public Msg parsePartialFrom(
                    com.google.protobuf.CodedInputStream input,
                    com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                    throws com.google.protobuf.InvalidProtocolBufferException {
                return new Msg(input, extensionRegistry);
            }
        };

        public static com.google.protobuf.Parser<Msg> parser() {
            return PARSER;
        }

        @java.lang.Override
        public com.google.protobuf.Parser<Msg> getParserForType() {
            return PARSER;
        }

        @java.lang.Override
        public Msg getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

    }

    public interface Msg2OrBuilder extends
            // @@protoc_insertion_point(interface_extends:com.crazymakercircle.netty.protocol.Msg2)
            com.google.protobuf.MessageOrBuilder {

        /**
         * <pre>
         * Unique ID number for this person.
         * </pre>
         *
         * <code>uint32 id = 1;</code>
         */
        int getId();

        /**
         * <code>string content = 2;</code>
         */
        java.lang.String getContent();

        /**
         * <code>string content = 2;</code>
         */
        com.google.protobuf.ByteString
        getContentBytes();
    }

    /**
     * Protobuf type {@code com.crazymakercircle.netty.protocol.Msg2}
     */
    public static final class Msg2 extends
            com.google.protobuf.GeneratedMessageV3 implements
            // @@protoc_insertion_point(message_implements:com.crazymakercircle.netty.protocol.Msg2)
            Msg2OrBuilder {
        private static final long serialVersionUID = 0L;

        // Use Msg2.newBuilder() to construct.
        private Msg2(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
            super(builder);
        }

        private Msg2() {
            id_ = 0;
            content_ = "";
        }

        @java.lang.Override
        public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
            return this.unknownFields;
        }

        private Msg2(
                com.google.protobuf.CodedInputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            this();
            if (extensionRegistry == null) {
                throw new java.lang.NullPointerException();
            }
            int mutable_bitField0_ = 0;
            com.google.protobuf.UnknownFieldSet.Builder unknownFields =
                    com.google.protobuf.UnknownFieldSet.newBuilder();
            try {
                boolean done = false;
                while (!done) {
                    int tag = input.readTag();
                    switch (tag) {
                        case 0:
                            done = true;
                            break;
                        case 8: {

                            id_ = input.readUInt32();
                            break;
                        }
                        case 18: {
                            java.lang.String s = input.readStringRequireUtf8();

                            content_ = s;
                            break;
                        }
                        default: {
                            if (!parseUnknownFieldProto3(
                                    input, unknownFields, extensionRegistry, tag)) {
                                done = true;
                            }
                            break;
                        }
                    }
                }
            } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage(this);
            } catch (java.io.IOException e) {
                throw new com.google.protobuf.InvalidProtocolBufferException(
                        e).setUnfinishedMessage(this);
            } finally {
                this.unknownFields = unknownFields.build();
                makeExtensionsImmutable();
            }
        }

        public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
            return MsgProtos.internal_static_com_crazymakercircle_netty_protocol_Msg2_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
            return internal_static_com_crazymakercircle_netty_protocol_Msg2_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            Msg2.class, Msg2.Builder.class);
        }

        public static final int ID_FIELD_NUMBER = 1;
        private int id_;

        /**
         * <pre>
         * Unique ID number for this person.
         * </pre>
         *
         * <code>uint32 id = 1;</code>
         */
        public int getId() {
            return id_;
        }

        public static final int CONTENT_FIELD_NUMBER = 2;
        private volatile java.lang.Object content_;

        /**
         * <code>string content = 2;</code>
         */
        public java.lang.String getContent() {
            java.lang.Object ref = content_;
            if (ref instanceof java.lang.String) {
                return (java.lang.String) ref;
            } else {
                com.google.protobuf.ByteString bs =
                        (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                content_ = s;
                return s;
            }
        }

        /**
         * <code>string content = 2;</code>
         */
        public com.google.protobuf.ByteString
        getContentBytes() {
            java.lang.Object ref = content_;
            if (ref instanceof java.lang.String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8(
                                (java.lang.String) ref);
                content_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        private byte memoizedIsInitialized = -1;

        @java.lang.Override
        public final boolean isInitialized() {
            byte isInitialized = memoizedIsInitialized;
            if (isInitialized == 1) return true;
            if (isInitialized == 0) return false;

            memoizedIsInitialized = 1;
            return true;
        }

        @java.lang.Override
        public void writeTo(com.google.protobuf.CodedOutputStream output)
                throws java.io.IOException {
            if (id_ != 0) {
                output.writeUInt32(1, id_);
            }
            if (!getContentBytes().isEmpty()) {
                com.google.protobuf.GeneratedMessageV3.writeString(output, 2, content_);
            }
            unknownFields.writeTo(output);
        }

        @java.lang.Override
        public int getSerializedSize() {
            int size = memoizedSize;
            if (size != -1) return size;

            size = 0;
            if (id_ != 0) {
                size += com.google.protobuf.CodedOutputStream
                        .computeUInt32Size(1, id_);
            }
            if (!getContentBytes().isEmpty()) {
                size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, content_);
            }
            size += unknownFields.getSerializedSize();
            memoizedSize = size;
            return size;
        }

        @java.lang.Override
        public boolean equals(final java.lang.Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Msg2)) {
                return super.equals(obj);
            }
            Msg2 other = (Msg2) obj;

            boolean result = true;
            result = result && (getId()
                    == other.getId());
            result = result && getContent()
                    .equals(other.getContent());
            result = result && unknownFields.equals(other.unknownFields);
            return result;
        }

        @java.lang.Override
        public int hashCode() {
            if (memoizedHashCode != 0) {
                return memoizedHashCode;
            }
            int hash = 41;
            hash = (19 * hash) + getDescriptor().hashCode();
            hash = (37 * hash) + ID_FIELD_NUMBER;
            hash = (53 * hash) + getId();
            hash = (37 * hash) + CONTENT_FIELD_NUMBER;
            hash = (53 * hash) + getContent().hashCode();
            hash = (29 * hash) + unknownFields.hashCode();
            memoizedHashCode = hash;
            return hash;
        }

        public static Msg2 parseFrom(
                java.nio.ByteBuffer data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static Msg2 parseFrom(
                java.nio.ByteBuffer data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static Msg2 parseFrom(
                com.google.protobuf.ByteString data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static Msg2 parseFrom(
                com.google.protobuf.ByteString data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static Msg2 parseFrom(byte[] data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static Msg2 parseFrom(
                byte[] data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static Msg2 parseFrom(java.io.InputStream input)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input);
        }

        public static Msg2 parseFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input, extensionRegistry);
        }

        public static Msg2 parseDelimitedFrom(java.io.InputStream input)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseDelimitedWithIOException(PARSER, input);
        }

        public static Msg2 parseDelimitedFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
        }

        public static Msg2 parseFrom(
                com.google.protobuf.CodedInputStream input)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input);
        }

        public static Msg2 parseFrom(
                com.google.protobuf.CodedInputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input, extensionRegistry);
        }

        @java.lang.Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Msg2 prototype) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
        }

        @java.lang.Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE
                    ? new Builder() : new Builder().mergeFrom(this);
        }

        @java.lang.Override
        protected Builder newBuilderForType(
                com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        /**
         * Protobuf type {@code com.crazymakercircle.netty.protocol.Msg2}
         */
        public static final class Builder extends
                com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
                // @@protoc_insertion_point(builder_implements:com.crazymakercircle.netty.protocol.Msg2)
                Msg2OrBuilder {
            public static final com.google.protobuf.Descriptors.Descriptor
            getDescriptor() {
                return MsgProtos.internal_static_com_crazymakercircle_netty_protocol_Msg2_descriptor;
            }

            @java.lang.Override
            protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
            internalGetFieldAccessorTable() {
                return internal_static_com_crazymakercircle_netty_protocol_Msg2_fieldAccessorTable
                        .ensureFieldAccessorsInitialized(
                                Msg2.class, Msg2.Builder.class);
            }

            // Construct using Msg2.newBuilder()
            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(
                    com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
                super(parent);
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                if (com.google.protobuf.GeneratedMessageV3
                        .alwaysUseFieldBuilders) {
                }
            }

            @java.lang.Override
            public Builder clear() {
                super.clear();
                id_ = 0;

                content_ = "";

                return this;
            }

            @java.lang.Override
            public com.google.protobuf.Descriptors.Descriptor
            getDescriptorForType() {
                return MsgProtos.internal_static_com_crazymakercircle_netty_protocol_Msg2_descriptor;
            }

            @java.lang.Override
            public Msg2 getDefaultInstanceForType() {
                return Msg2.getDefaultInstance();
            }

            @java.lang.Override
            public Msg2 build() {
                Msg2 result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException(result);
                }
                return result;
            }

            @java.lang.Override
            public Msg2 buildPartial() {
                Msg2 result = new Msg2(this);
                result.id_ = id_;
                result.content_ = content_;
                onBuilt();
                return result;
            }

            @java.lang.Override
            public Builder clone() {
                return (Builder) super.clone();
            }

            @java.lang.Override
            public Builder setField(
                    com.google.protobuf.Descriptors.FieldDescriptor field,
                    java.lang.Object value) {
                return (Builder) super.setField(field, value);
            }

            @java.lang.Override
            public Builder clearField(
                    com.google.protobuf.Descriptors.FieldDescriptor field) {
                return (Builder) super.clearField(field);
            }

            @java.lang.Override
            public Builder clearOneof(
                    com.google.protobuf.Descriptors.OneofDescriptor oneof) {
                return (Builder) super.clearOneof(oneof);
            }

            @java.lang.Override
            public Builder setRepeatedField(
                    com.google.protobuf.Descriptors.FieldDescriptor field,
                    int index, java.lang.Object value) {
                return (Builder) super.setRepeatedField(field, index, value);
            }

            @java.lang.Override
            public Builder addRepeatedField(
                    com.google.protobuf.Descriptors.FieldDescriptor field,
                    java.lang.Object value) {
                return (Builder) super.addRepeatedField(field, value);
            }

            @java.lang.Override
            public Builder mergeFrom(com.google.protobuf.Message other) {
                if (other instanceof Msg2) {
                    return mergeFrom((Msg2) other);
                } else {
                    super.mergeFrom(other);
                    return this;
                }
            }

            public Builder mergeFrom(Msg2 other) {
                if (other == Msg2.getDefaultInstance()) return this;
                if (other.getId() != 0) {
                    setId(other.getId());
                }
                if (!other.getContent().isEmpty()) {
                    content_ = other.content_;
                    onChanged();
                }
                this.mergeUnknownFields(other.unknownFields);
                onChanged();
                return this;
            }

            @java.lang.Override
            public final boolean isInitialized() {
                return true;
            }

            @java.lang.Override
            public Builder mergeFrom(
                    com.google.protobuf.CodedInputStream input,
                    com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                    throws java.io.IOException {
                Msg2 parsedMessage = null;
                try {
                    parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                    parsedMessage = (Msg2) e.getUnfinishedMessage();
                    throw e.unwrapIOException();
                } finally {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                }
                return this;
            }

            private int id_;

            /**
             * <pre>
             * Unique ID number for this person.
             * </pre>
             *
             * <code>uint32 id = 1;</code>
             */
            public int getId() {
                return id_;
            }

            /**
             * <pre>
             * Unique ID number for this person.
             * </pre>
             *
             * <code>uint32 id = 1;</code>
             */
            public Builder setId(int value) {

                id_ = value;
                onChanged();
                return this;
            }

            /**
             * <pre>
             * Unique ID number for this person.
             * </pre>
             *
             * <code>uint32 id = 1;</code>
             */
            public Builder clearId() {

                id_ = 0;
                onChanged();
                return this;
            }

            private java.lang.Object content_ = "";

            /**
             * <code>string content = 2;</code>
             */
            public java.lang.String getContent() {
                java.lang.Object ref = content_;
                if (!(ref instanceof java.lang.String)) {
                    com.google.protobuf.ByteString bs =
                            (com.google.protobuf.ByteString) ref;
                    java.lang.String s = bs.toStringUtf8();
                    content_ = s;
                    return s;
                } else {
                    return (java.lang.String) ref;
                }
            }

            /**
             * <code>string content = 2;</code>
             */
            public com.google.protobuf.ByteString
            getContentBytes() {
                java.lang.Object ref = content_;
                if (ref instanceof String) {
                    com.google.protobuf.ByteString b =
                            com.google.protobuf.ByteString.copyFromUtf8(
                                    (java.lang.String) ref);
                    content_ = b;
                    return b;
                } else {
                    return (com.google.protobuf.ByteString) ref;
                }
            }

            /**
             * <code>string content = 2;</code>
             */
            public Builder setContent(
                    java.lang.String value) {
                if (value == null) {
                    throw new NullPointerException();
                }

                content_ = value;
                onChanged();
                return this;
            }

            /**
             * <code>string content = 2;</code>
             */
            public Builder clearContent() {

                content_ = getDefaultInstance().getContent();
                onChanged();
                return this;
            }

            /**
             * <code>string content = 2;</code>
             */
            public Builder setContentBytes(
                    com.google.protobuf.ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                checkByteStringIsUtf8(value);

                content_ = value;
                onChanged();
                return this;
            }

            @java.lang.Override
            public final Builder setUnknownFields(
                    final com.google.protobuf.UnknownFieldSet unknownFields) {
                return super.setUnknownFieldsProto3(unknownFields);
            }

            @java.lang.Override
            public final Builder mergeUnknownFields(
                    final com.google.protobuf.UnknownFieldSet unknownFields) {
                return super.mergeUnknownFields(unknownFields);
            }


            // @@protoc_insertion_point(builder_scope:com.crazymakercircle.netty.protocol.Msg2)
        }

        // @@protoc_insertion_point(class_scope:com.crazymakercircle.netty.protocol.Msg2)
        private static final Msg2 DEFAULT_INSTANCE;

        static {
            DEFAULT_INSTANCE = new Msg2();
        }

        public static Msg2 getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        private static final com.google.protobuf.Parser<Msg2>
                PARSER = new com.google.protobuf.AbstractParser<Msg2>() {
            @java.lang.Override
            public Msg2 parsePartialFrom(
                    com.google.protobuf.CodedInputStream input,
                    com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                    throws com.google.protobuf.InvalidProtocolBufferException {
                return new Msg2(input, extensionRegistry);
            }
        };

        public static com.google.protobuf.Parser<Msg2> parser() {
            return PARSER;
        }

        @java.lang.Override
        public com.google.protobuf.Parser<Msg2> getParserForType() {
            return PARSER;
        }

        @java.lang.Override
        public Msg2 getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

    }

    public interface Msg3OrBuilder extends
            // @@protoc_insertion_point(interface_extends:com.crazymakercircle.netty.protocol.Msg3)
            com.google.protobuf.MessageOrBuilder {

        /**
         * <pre>
         * Unique ID number for this person.
         * </pre>
         *
         * <code>uint32 id = 1;</code>
         */
        int getId();

        /**
         * <code>string content = 2;</code>
         */
        java.lang.String getContent();

        /**
         * <code>string content = 2;</code>
         */
        com.google.protobuf.ByteString
        getContentBytes();
    }

    /**
     * Protobuf type {@code com.crazymakercircle.netty.protocol.Msg3}
     */
    public static final class Msg3 extends
            com.google.protobuf.GeneratedMessageV3 implements
            // @@protoc_insertion_point(message_implements:com.crazymakercircle.netty.protocol.Msg3)
            Msg3OrBuilder {
        private static final long serialVersionUID = 0L;

        // Use Msg3.newBuilder() to construct.
        private Msg3(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
            super(builder);
        }

        private Msg3() {
            id_ = 0;
            content_ = "";
        }

        @java.lang.Override
        public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
            return this.unknownFields;
        }

        private Msg3(
                com.google.protobuf.CodedInputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            this();
            if (extensionRegistry == null) {
                throw new java.lang.NullPointerException();
            }
            int mutable_bitField0_ = 0;
            com.google.protobuf.UnknownFieldSet.Builder unknownFields =
                    com.google.protobuf.UnknownFieldSet.newBuilder();
            try {
                boolean done = false;
                while (!done) {
                    int tag = input.readTag();
                    switch (tag) {
                        case 0:
                            done = true;
                            break;
                        case 8: {

                            id_ = input.readUInt32();
                            break;
                        }
                        case 18: {
                            java.lang.String s = input.readStringRequireUtf8();

                            content_ = s;
                            break;
                        }
                        default: {
                            if (!parseUnknownFieldProto3(
                                    input, unknownFields, extensionRegistry, tag)) {
                                done = true;
                            }
                            break;
                        }
                    }
                }
            } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage(this);
            } catch (java.io.IOException e) {
                throw new com.google.protobuf.InvalidProtocolBufferException(
                        e).setUnfinishedMessage(this);
            } finally {
                this.unknownFields = unknownFields.build();
                makeExtensionsImmutable();
            }
        }

        public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
            return internal_static_com_crazymakercircle_netty_protocol_Msg3_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
            return internal_static_com_crazymakercircle_netty_protocol_Msg3_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            Msg3.class, Msg3.Builder.class);
        }

        public static final int ID_FIELD_NUMBER = 1;
        private int id_;

        /**
         * <pre>
         * Unique ID number for this person.
         * </pre>
         *
         * <code>uint32 id = 1;</code>
         */
        public int getId() {
            return id_;
        }

        public static final int CONTENT_FIELD_NUMBER = 2;
        private volatile java.lang.Object content_;

        /**
         * <code>string content = 2;</code>
         */
        public java.lang.String getContent() {
            java.lang.Object ref = content_;
            if (ref instanceof java.lang.String) {
                return (java.lang.String) ref;
            } else {
                com.google.protobuf.ByteString bs =
                        (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                content_ = s;
                return s;
            }
        }

        /**
         * <code>string content = 2;</code>
         */
        public com.google.protobuf.ByteString
        getContentBytes() {
            java.lang.Object ref = content_;
            if (ref instanceof java.lang.String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8(
                                (java.lang.String) ref);
                content_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        private byte memoizedIsInitialized = -1;

        @java.lang.Override
        public final boolean isInitialized() {
            byte isInitialized = memoizedIsInitialized;
            if (isInitialized == 1) return true;
            if (isInitialized == 0) return false;

            memoizedIsInitialized = 1;
            return true;
        }

        @java.lang.Override
        public void writeTo(com.google.protobuf.CodedOutputStream output)
                throws java.io.IOException {
            if (id_ != 0) {
                output.writeUInt32(1, id_);
            }
            if (!getContentBytes().isEmpty()) {
                com.google.protobuf.GeneratedMessageV3.writeString(output, 2, content_);
            }
            unknownFields.writeTo(output);
        }

        @java.lang.Override
        public int getSerializedSize() {
            int size = memoizedSize;
            if (size != -1) return size;

            size = 0;
            if (id_ != 0) {
                size += com.google.protobuf.CodedOutputStream
                        .computeUInt32Size(1, id_);
            }
            if (!getContentBytes().isEmpty()) {
                size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, content_);
            }
            size += unknownFields.getSerializedSize();
            memoizedSize = size;
            return size;
        }

        @java.lang.Override
        public boolean equals(final java.lang.Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Msg3)) {
                return super.equals(obj);
            }
            Msg3 other = (Msg3) obj;

            boolean result = true;
            result = result && (getId()
                    == other.getId());
            result = result && getContent()
                    .equals(other.getContent());
            result = result && unknownFields.equals(other.unknownFields);
            return result;
        }

        @java.lang.Override
        public int hashCode() {
            if (memoizedHashCode != 0) {
                return memoizedHashCode;
            }
            int hash = 41;
            hash = (19 * hash) + getDescriptor().hashCode();
            hash = (37 * hash) + ID_FIELD_NUMBER;
            hash = (53 * hash) + getId();
            hash = (37 * hash) + CONTENT_FIELD_NUMBER;
            hash = (53 * hash) + getContent().hashCode();
            hash = (29 * hash) + unknownFields.hashCode();
            memoizedHashCode = hash;
            return hash;
        }

        public static Msg3 parseFrom(
                java.nio.ByteBuffer data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static Msg3 parseFrom(
                java.nio.ByteBuffer data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static Msg3 parseFrom(
                com.google.protobuf.ByteString data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static Msg3 parseFrom(
                com.google.protobuf.ByteString data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static Msg3 parseFrom(byte[] data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static Msg3 parseFrom(
                byte[] data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static Msg3 parseFrom(java.io.InputStream input)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input);
        }

        public static Msg3 parseFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input, extensionRegistry);
        }

        public static Msg3 parseDelimitedFrom(java.io.InputStream input)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseDelimitedWithIOException(PARSER, input);
        }

        public static Msg3 parseDelimitedFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
        }

        public static Msg3 parseFrom(
                com.google.protobuf.CodedInputStream input)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input);
        }

        public static Msg3 parseFrom(
                com.google.protobuf.CodedInputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input, extensionRegistry);
        }

        @java.lang.Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Msg3 prototype) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
        }

        @java.lang.Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE
                    ? new Builder() : new Builder().mergeFrom(this);
        }

        @java.lang.Override
        protected Builder newBuilderForType(
                com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        /**
         * Protobuf type {@code com.crazymakercircle.netty.protocol.Msg3}
         */
        public static final class Builder extends
                com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
                // @@protoc_insertion_point(builder_implements:com.crazymakercircle.netty.protocol.Msg3)
                Msg3OrBuilder {
            public static final com.google.protobuf.Descriptors.Descriptor
            getDescriptor() {
                return internal_static_com_crazymakercircle_netty_protocol_Msg3_descriptor;
            }

            @java.lang.Override
            protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
            internalGetFieldAccessorTable() {
                return internal_static_com_crazymakercircle_netty_protocol_Msg3_fieldAccessorTable
                        .ensureFieldAccessorsInitialized(
                                Msg3.class, Msg3.Builder.class);
            }

            // Construct using Msg3.newBuilder()
            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(
                    com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
                super(parent);
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                if (com.google.protobuf.GeneratedMessageV3
                        .alwaysUseFieldBuilders) {
                }
            }

            @java.lang.Override
            public Builder clear() {
                super.clear();
                id_ = 0;

                content_ = "";

                return this;
            }

            @java.lang.Override
            public com.google.protobuf.Descriptors.Descriptor
            getDescriptorForType() {
                return internal_static_com_crazymakercircle_netty_protocol_Msg3_descriptor;
            }

            @java.lang.Override
            public Msg3 getDefaultInstanceForType() {
                return Msg3.getDefaultInstance();
            }

            @java.lang.Override
            public Msg3 build() {
                Msg3 result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException(result);
                }
                return result;
            }

            @java.lang.Override
            public Msg3 buildPartial() {
                Msg3 result = new Msg3(this);
                result.id_ = id_;
                result.content_ = content_;
                onBuilt();
                return result;
            }

            @java.lang.Override
            public Builder clone() {
                return (Builder) super.clone();
            }

            @java.lang.Override
            public Builder setField(
                    com.google.protobuf.Descriptors.FieldDescriptor field,
                    java.lang.Object value) {
                return (Builder) super.setField(field, value);
            }

            @java.lang.Override
            public Builder clearField(
                    com.google.protobuf.Descriptors.FieldDescriptor field) {
                return (Builder) super.clearField(field);
            }

            @java.lang.Override
            public Builder clearOneof(
                    com.google.protobuf.Descriptors.OneofDescriptor oneof) {
                return (Builder) super.clearOneof(oneof);
            }

            @java.lang.Override
            public Builder setRepeatedField(
                    com.google.protobuf.Descriptors.FieldDescriptor field,
                    int index, java.lang.Object value) {
                return (Builder) super.setRepeatedField(field, index, value);
            }

            @java.lang.Override
            public Builder addRepeatedField(
                    com.google.protobuf.Descriptors.FieldDescriptor field,
                    java.lang.Object value) {
                return (Builder) super.addRepeatedField(field, value);
            }

            @java.lang.Override
            public Builder mergeFrom(com.google.protobuf.Message other) {
                if (other instanceof Msg3) {
                    return mergeFrom((Msg3) other);
                } else {
                    super.mergeFrom(other);
                    return this;
                }
            }

            public Builder mergeFrom(Msg3 other) {
                if (other == Msg3.getDefaultInstance()) return this;
                if (other.getId() != 0) {
                    setId(other.getId());
                }
                if (!other.getContent().isEmpty()) {
                    content_ = other.content_;
                    onChanged();
                }
                this.mergeUnknownFields(other.unknownFields);
                onChanged();
                return this;
            }

            @java.lang.Override
            public final boolean isInitialized() {
                return true;
            }

            @java.lang.Override
            public Builder mergeFrom(
                    com.google.protobuf.CodedInputStream input,
                    com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                    throws java.io.IOException {
                Msg3 parsedMessage = null;
                try {
                    parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                    parsedMessage = (Msg3) e.getUnfinishedMessage();
                    throw e.unwrapIOException();
                } finally {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                }
                return this;
            }

            private int id_;

            /**
             * <pre>
             * Unique ID number for this person.
             * </pre>
             *
             * <code>uint32 id = 1;</code>
             */
            public int getId() {
                return id_;
            }

            /**
             * <pre>
             * Unique ID number for this person.
             * </pre>
             *
             * <code>uint32 id = 1;</code>
             */
            public Builder setId(int value) {

                id_ = value;
                onChanged();
                return this;
            }

            /**
             * <pre>
             * Unique ID number for this person.
             * </pre>
             *
             * <code>uint32 id = 1;</code>
             */
            public Builder clearId() {

                id_ = 0;
                onChanged();
                return this;
            }

            private java.lang.Object content_ = "";

            /**
             * <code>string content = 2;</code>
             */
            public java.lang.String getContent() {
                java.lang.Object ref = content_;
                if (!(ref instanceof java.lang.String)) {
                    com.google.protobuf.ByteString bs =
                            (com.google.protobuf.ByteString) ref;
                    java.lang.String s = bs.toStringUtf8();
                    content_ = s;
                    return s;
                } else {
                    return (java.lang.String) ref;
                }
            }

            /**
             * <code>string content = 2;</code>
             */
            public com.google.protobuf.ByteString
            getContentBytes() {
                java.lang.Object ref = content_;
                if (ref instanceof String) {
                    com.google.protobuf.ByteString b =
                            com.google.protobuf.ByteString.copyFromUtf8(
                                    (java.lang.String) ref);
                    content_ = b;
                    return b;
                } else {
                    return (com.google.protobuf.ByteString) ref;
                }
            }

            /**
             * <code>string content = 2;</code>
             */
            public Builder setContent(
                    java.lang.String value) {
                if (value == null) {
                    throw new NullPointerException();
                }

                content_ = value;
                onChanged();
                return this;
            }

            /**
             * <code>string content = 2;</code>
             */
            public Builder clearContent() {

                content_ = getDefaultInstance().getContent();
                onChanged();
                return this;
            }

            /**
             * <code>string content = 2;</code>
             */
            public Builder setContentBytes(
                    com.google.protobuf.ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                checkByteStringIsUtf8(value);

                content_ = value;
                onChanged();
                return this;
            }

            @java.lang.Override
            public final Builder setUnknownFields(
                    final com.google.protobuf.UnknownFieldSet unknownFields) {
                return super.setUnknownFieldsProto3(unknownFields);
            }

            @java.lang.Override
            public final Builder mergeUnknownFields(
                    final com.google.protobuf.UnknownFieldSet unknownFields) {
                return super.mergeUnknownFields(unknownFields);
            }


            // @@protoc_insertion_point(builder_scope:com.crazymakercircle.netty.protocol.Msg3)
        }

        // @@protoc_insertion_point(class_scope:com.crazymakercircle.netty.protocol.Msg3)
        private static final Msg3 DEFAULT_INSTANCE;

        static {
            DEFAULT_INSTANCE = new Msg3();
        }

        public static Msg3 getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        private static final com.google.protobuf.Parser<Msg3>
                PARSER = new com.google.protobuf.AbstractParser<Msg3>() {
            @java.lang.Override
            public Msg3 parsePartialFrom(
                    com.google.protobuf.CodedInputStream input,
                    com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                    throws com.google.protobuf.InvalidProtocolBufferException {
                return new Msg3(input, extensionRegistry);
            }
        };

        public static com.google.protobuf.Parser<Msg3> parser() {
            return PARSER;
        }

        @java.lang.Override
        public com.google.protobuf.Parser<Msg3> getParserForType() {
            return PARSER;
        }

        @java.lang.Override
        public Msg3 getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

    }

    private static final com.google.protobuf.Descriptors.Descriptor
            internal_static_com_crazymakercircle_netty_protocol_Msg_descriptor;
    private static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
            internal_static_com_crazymakercircle_netty_protocol_Msg_fieldAccessorTable;
    private static final com.google.protobuf.Descriptors.Descriptor
            internal_static_com_crazymakercircle_netty_protocol_Msg2_descriptor;
    private static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
            internal_static_com_crazymakercircle_netty_protocol_Msg2_fieldAccessorTable;
    private static final com.google.protobuf.Descriptors.Descriptor
            internal_static_com_crazymakercircle_netty_protocol_Msg3_descriptor;
    private static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
            internal_static_com_crazymakercircle_netty_protocol_Msg3_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor
    getDescriptor() {
        return descriptor;
    }

    private static com.google.protobuf.Descriptors.FileDescriptor
            descriptor;

    static {
        java.lang.String[] descriptorData = {
                "\n\tMsg.proto\022#com.crazymakercircle.netty." +
                        "protocol\"\"\n\003Msg\022\n\n\002id\030\001 \001(\r\022\017\n\007content\030\002" +
                        " \001(\t\"#\n\004Msg2\022\n\n\002id\030\001 \001(\r\022\017\n\007content\030\002 \001(" +
                        "\t\"#\n\004Msg3\022\n\n\002id\030\001 \001(\r\022\017\n\007content\030\002 \001(\tB0" +
                        "\n#com.crazymakercircle.netty.protocolB\tM" +
                        "sgProtosb\006proto3"
        };
        com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
                new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
                    public com.google.protobuf.ExtensionRegistry assignDescriptors(
                            com.google.protobuf.Descriptors.FileDescriptor root) {
                        descriptor = root;
                        return null;
                    }
                };
        com.google.protobuf.Descriptors.FileDescriptor
                .internalBuildGeneratedFileFrom(descriptorData,
                        new com.google.protobuf.Descriptors.FileDescriptor[]{
                        }, assigner);
        internal_static_com_crazymakercircle_netty_protocol_Msg_descriptor =
                getDescriptor().getMessageTypes().get(0);
        internal_static_com_crazymakercircle_netty_protocol_Msg_fieldAccessorTable = new
                com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                internal_static_com_crazymakercircle_netty_protocol_Msg_descriptor,
                new java.lang.String[]{"Id", "Content",});
        internal_static_com_crazymakercircle_netty_protocol_Msg2_descriptor =
                getDescriptor().getMessageTypes().get(1);
        internal_static_com_crazymakercircle_netty_protocol_Msg2_fieldAccessorTable = new
                com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                internal_static_com_crazymakercircle_netty_protocol_Msg2_descriptor,
                new java.lang.String[]{"Id", "Content",});
        internal_static_com_crazymakercircle_netty_protocol_Msg3_descriptor =
                getDescriptor().getMessageTypes().get(2);
        internal_static_com_crazymakercircle_netty_protocol_Msg3_fieldAccessorTable = new
                com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                internal_static_com_crazymakercircle_netty_protocol_Msg3_descriptor,
                new java.lang.String[]{"Id", "Content",});
    }

}
