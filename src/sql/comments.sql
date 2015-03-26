-- name: insert-comment!
-- Inserts a comment into the main processing table
INSERT INTO comments 
  (comment_id, comment_text, link_id, comment_ts, deleted)
VALUES 
  (:comment-id, :comment-text, :link-id, :comment-ts, false);
